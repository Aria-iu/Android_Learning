// groovy -cp jfreechart-1.5.3.jar:jcommon-1.0.24.jar 10.groovy

import groovy.swing.SwingBuilder
import javax.management.ObjectName 
import javax.management.remote.JMXConnectorFactory as JmxFactory 
import javax.management.remote.JMXServiceURL as JmxUrl 
import javax.swing.WindowConstants as WC 
import org.jfree.chart.ChartFactory 
import org.jfree.data.category.DefaultCategoryDataset as Dataset 
import org.jfree.chart.plot.PlotOrientation as Orientation 

def serverUrl = 'service:jmx:rmi:///jndi/rmi://localhost:9004/jmxrmi' 
def server = JmxFactory.connect(new JmxUrl(serverUrl)).MBeanServerConnection 
def serverInfo = new GroovyMBean(server, 'Catalina:type=Server').serverInfo 
println "Connected to: $serverInfo" 

def query = new ObjectName('Catalina:*') 
String[] allNames = server.queryNames(query, null) 

def modules = allNames.findAll { name -> 
   name.contains('j2eeType=WebModule') 
}.collect { new GroovyMBean(server, it) }
  
println "Found ${modules.size()} web modules. Processing ..." 
def dataset = new Dataset() 

modules.each { m ->
   println m.name()
   if (m.processingTime != null) {
       dataset.addValue m.processingTime, 'Processing Time', m.path 
   }
}

// Create and display the chart
def swingBuilder = new SwingBuilder()
swingBuilder.frame(title: 'Web Module Processing Times', defaultCloseOperation: WC.EXIT_ON_CLOSE, size: [800, 600]) {
    def chart = ChartFactory.createBarChart(
        'Web Module Processing Times', 
        'Web Module', 
        'Time (ms)', 
        dataset, 
        Orientation.VERTICAL, 
        true, 
        true, 
        false
    )
    def chartPanel = new org.jfree.chart.ChartPanel(chart)
    add(chartPanel)
}

// Make the frame visible
swingBuilder.frame.visible = true
