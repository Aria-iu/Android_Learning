print "Hello\n"

for(String it: new String[] {"Red","SSS"}){
    if (it.length()<4){
        System.out.println(it);
    }
}

["Rod", "Carlos", "Chris"].findAll{it.size() < 4}.each{println it}

def list = [1,2]
list.add(3)
list << 4
list+=5
def map = [cars:2,boats:3]
println list.getClass()
println map.getClass()

map.cars = 4
map.planes = 33
println map.cars

def list2 = []

10.times {println "hi"}
