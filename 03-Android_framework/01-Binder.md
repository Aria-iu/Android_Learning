# 概述
Binder 用于完成进程间通信（IPC），把多个进程“别”在一起。
Binder 在 Linux 层面属于是一个驱动，但是它不是适配硬件的驱动。从线程角度看，Binder驱动运行在内核态，了用户程序需要通过系统调用来使用Binder。

# Binder 框架
Binder 是一种架构，提供了服务端接口、Binder驱动、客户端接口三个模块。

一个Binder服务端就是一个Binder类的对象，创建时会启动一个隐藏的线程。这个线程会接受Binder驱动发送的消息，收到消息后，会执行到Binder对象中的onTransact()函数，并按照该函数的参数执行不同的服务代码。onTransact()函数的参数来源是客户端调用transact()函数时输入的。

任意一个Binder服务端被创建时，同样会在Binder驱动中创建一个mRemote对象，类型也是Binder类。客户访问远程服务时，都是通过mRemote对象。

客户端要想访问远程服务，必须获取远程服务在Binder对象中对应的mRemote引用。获得该mRemote对象后，就可以调用其transact()方法，而在Binder驱动中，mRemote对象也重载了 transact()方法，重载的内容主要包括以下几项。
    1. 以线程间消息通信的模式，向服务端发送客户端传递过来的参数。
    2. 挂起当前线程，当前线程正是客户端线程，并等待服务端线程执行完指定服务函数后通知
    3. 接收到服务端线程的通知，然后继续执行客户端线程，并返回到客户端代码区

Server 端：
```Java
public class MusicPlayerServer extends Binder {
    @Override
    protected boolean onTransact(int code,Parcel data,Parcel reply,int flags) throws RemoteException{
        switch(code) {
            case 1000:
                data.enforceInterface("MusicPlayerService");
                String filePath = data.readString();
                start(filePath);
                break;
            ...
            ...
        }

        return super.onTransact(code,data,reply,flags);
    }

    public void start(String filePath){
    }

    public void stop(){}
}
```
**当要启动该服务时，只需要初始化一个MusicPlayerService对象即可。**

客户端：
```Java
IBinder mRemote = null;
String filePath = "/sdcard/music/some.mp3";
int code = 1000;
Parcel data = Parcel.obtain();
Parcel reply = Parcel.obtain();
data.writeInterfaceToken("MusicPlayerService");
data.writeString(filePath);
mRemote.transact(code,data,reply,0);
IBinder binder = reply.readStrongBinder();
reply.recycle();
data.recycle();
```
包裹不是客户端自己创建的，而是调用 Parcel.obtain()申请的。data和reply变量都由客户端提供，reply变量用户服务端把返回的结果放入其中。
writeInterfaceToken()方法标注远程服务名称。
writeString()方法用于向包裹中添加一个 String变量。

**调用该方法后，客户端线程进入 Binder驱动，Binder驱动就会挂起当前线程，并向远程服务发送一个消息，消息中包含了客户端传进来的包裹。服务端拿到包裹后，会对包裹进行拆解，然后执行指定的服务函数，执行完毕后，再把执行结果放入客户端提供的reply包裹中。然后服务端向 Binder驱动发送一个 notify的消息，从而使得客户端线程从Binder驱动代码区返回到客户端代码区。**

transactO的最后一个参数的含义是执行IP C 调用的模式，分为两种：一种是双向，用常量0表示，其含义是服务端执行完指定服务后会返回一定的数据；另一种是单向，用常量1表示，其含义是不返回任何数据。

# Service 类
手工编写Binder服务端和客户端的过程存在两个重要问题：
1.客户端如何获得服务端的Binder对象引用
2.客户端和服务端必须事先约定好两件事情：服务端函数的参数在包裹中的顺序，服务端不同函数的int型标识。

用Binder为了提供一个全局服务，所谓的“全局”，是指系统中的任何应用程序都可以访问

AmS提供了 startService()函数用于启动客户服务，而对客户端来讲，可以使用以下两个函数来和一个服务建立连接
    public ComponentName startService(Intent intent);   // 该函数用于启动intent指定的服务
    public boolean bindService(Intent service,ServiceConnection conn,int flags) ;   // 该函数用于绑定一个服务

# 系统服务
getSystemService(String serviceName)方法获取一个系统服务

getSystemService()函数的实现是在Contextlmpl类中，该函数所返回的Service比较多。这些Service—般都由ServiceManager管理。
ServiceManager是一个独立进程，其作用如名称所示，管理各种系统服务

ServiceManager本身也是一个Service，Framework提供了一个系统函数，可以获取该Service对应的Binder引用，那就是BinderIntemal.getContextObject()。该静态函数返回ServiceManager后，就可以通过ServiceManager提供的方法获取其他系统Service的Binder引用。

这种设计的好处是系统中仅暴露一个全局Binder引用，那就是ServiceManager，而其他系统服务则可以隐藏起来，从而有助于系统服务的扩展，以及调用系统服务的安全检查。其他系统服务在启动时，首先把自己的Binder对象传递给ServiceManager，即所谓的注册(addService)。


