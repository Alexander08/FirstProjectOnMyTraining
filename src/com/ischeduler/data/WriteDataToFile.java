package com.ischeduler.data;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.ischeduler.domain.Event;
import com.ischeduler.domain.EventKeeper;


public class WriteDataToFile implements ActionListener {

    private JFileChooser       fileChooser;

    private File               file;

    private FileOutputStream   oStream;
    private ObjectOutputStream outputStream;
    
    private EventKeeper eventList;

    public WriteDataToFile( EventKeeper ek) {

        this.fileChooser = new JFileChooser();
        this.eventList = new EventKeeper();
        this.eventList.setEventList(ek.getEventList());
    }

    public void prepareOutputStream() {

        try {

            this.oStream = new FileOutputStream(this.file);
            this.outputStream = new ObjectOutputStream(this.oStream);

        } catch (FileNotFoundException e) {

            System.err.println("File not Found!!!");
            e.printStackTrace();

        } catch (IOException e) {

            System.err.println("Some I/O Exception at initialization!");
            e.printStackTrace();
        }
    }

    public void closeOutput() {

        try {

            this.outputStream.close();

        } catch (IOException e) {

            System.err.println("Something goes wrong at closing file!");
            e.printStackTrace();
        }
    }
    
    public void writeToFile(Object obj){
        
            try {
                @SuppressWarnings("unchecked")
                List<Event> myList = (List<Event>) obj;
                ObjectOutputStream os = this.getOutputStream();
                os.writeObject(myList);
                this.setOutputStream(os);
            
            } catch (IOException e) {
            
                System.err.println("Object doesnt written");
                e.printStackTrace();
            
            }finally{
            
              closeOutput();
            }
        }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        String message = "Error!";
        int resultValue = this.fileChooser.showSaveDialog(null);
        
        if(resultValue == JFileChooser.APPROVE_OPTION){
            
            this.file = this.fileChooser.getSelectedFile();
            prepareOutputStream();
            writeToFile(this.eventList.getEventList());
            closeOutput();
            message = "Data saved to file";
        }
        JOptionPane.showMessageDialog(null, message);
    }

    public JFileChooser getFileChooser() {
        return fileChooser;
    }

    public void setFileChooser(JFileChooser fileChooser) {
        this.fileChooser = fileChooser;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public FileOutputStream getoStream() {
        return oStream;
    }

    public void setoStream(FileOutputStream oStream) {
        this.oStream = oStream;
    }

    public ObjectOutputStream getOutputStream() {
        return outputStream;
    }

    public void setOutputStream(ObjectOutputStream outputStream) {
        this.outputStream = outputStream;
    }

}
