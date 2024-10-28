import groovy.swing.SwingBuilder 
import javax.swing.* 

// Create a builder 
def myapp = new SwingBuilder()

// Compose the builder 
def myframe = myapp.frame(title: 'Tutorials Point', location: [200, 200], 
    size: [400, 300], defaultCloseOperation: WindowConstants.EXIT_ON_CLOSE) {         
    label(text: 'Hello world')
}

// The following statement is used for displaying the form 
myframe.setVisible(true)
