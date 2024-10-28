class Example { 
    static String file_path = "/home/zyc/asm.asm";
    static void main(String[] args) { 
    
        new File(file_path).withWriter('utf-8') {
            writer -> writer.writeLine 'Hello World'
        } 
        File file = new File(file_path)
        println "The file ${file.absolutePath} has ${file.length()} bytes"
        
    }
}