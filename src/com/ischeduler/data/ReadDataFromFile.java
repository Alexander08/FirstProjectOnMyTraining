package com.ischeduler.data;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.ischeduler.domain.Event;
import com.ischeduler.domain.EventKeeper;

public class ReadDataFromFile implements ActionListener {



    private JFileChooser      fileChooser;
    private File              file;

    private FileInputStream   iStream;
    private ObjectInputStream inputStream;

    private final EventKeeper eventList;

    public ReadDataFromFile(EventKeeper ek) {

        this.fileChooser = new JFileChooser();
        this.eventList = ek;
    }

    public void prepareInputStream() {

        try {

            this.iStream = new FileInputStream(this.file);
            this.inputStream = new ObjectInputStream(this.iStream);

        } catch (FileNotFoundException e) {

            System.err.println("File not Found!!!");
            e.printStackTrace();

        } catch (IOException e) {

            System.err.println("Some I/O Exception at initialization!");
            e.printStackTrace();
        }

    }

    public void closeInput() {

        try {

            this.inputStream.close();

        } catch (IOException e) {

            System.err.println("Something goes wrong at closing file!");
            e.printStackTrace();
        }
    }

    public Object readFromFile() {

        Object obj = null;
        try {

            obj = this.getInputStream().readObject();

        } catch (IOException e) {

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {

            closeInput();
        }
        return obj;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String message = "Error!";
        int resultValue = this.fileChooser.showOpenDialog(null);

        if (resultValue == JFileChooser.APPROVE_OPTION) {

            this.file = this.fileChooser.getSelectedFile();
            prepareInputStream();

            List<Event> list = (List<Event>) readFromFile();
            this.eventList.setEventList(list);
            closeInput();
            message = "Data load from file";
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

    public FileInputStream getiStream() {
        return iStream;
    }

    public void setiStream(FileInputStream iStream) {
        this.iStream = iStream;
    }

    public ObjectInputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(ObjectInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public EventKeeper getEventList() {
        return eventList;
    }

}
