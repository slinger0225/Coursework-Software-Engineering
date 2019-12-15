import java.io.IOException;
import java.io.*;
import java.io.Serializable;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.util.Calendar;

/**
 * This class contains basic input and output method for Student.
 * The data of each student is saved in a txt file named with the student's QM number.
 *
 * @author Group 44
 * @version 2.0
 */
public class StudentIO {

 
    private Calendar calendar = Calendar.getInstance();
    private int month = calendar.get(Calendar.MONTH);
    private int date = calendar.get(Calendar.DATE);
    private int hour = calendar.get(Calendar.HOUR_OF_DAY);
    private int minute = calendar.get(Calendar.MINUTE);
    private int time = ((month * 30 + date) * 24 + hour) * 60 + minute;

    //public Student student = new Student("161194886", "Zeyuan Dong", "Dongzeyuan@bupt.edu.cn");

/*
    public static void getStudentDataFromChart() {
        student1.setQMNum("161194886");
        student1.setStudentName("Zeyuan Dong");
        student1.setEmail("jp161194886@qmul.ac.uk");
    }
   */
    

    public Student studentInput(String IDNumber) throws IOException, ClassNotFoundException {
        Student student = new Student();
        try {
            File file1 = new File(""+IDNumber);
            FileInputStream fis1 = new FileInputStream(file1);
            ObjectInputStream iis1 = new ObjectInputStream(fis1);

            student = (Student) iis1.readObject();
            iis1.close();
            //System.out.println(student.getQMNum());
            //System.out.println(student.getStudentName());
            //System.out.println(student.getEmail());
            //System.out.println(student.getCurrentTime());
            //System.out.println(student.getTotalTime());
            //System.out.println(student.getDifferentDay());
        } catch (IOException e) {
            //   File file1=new File("/Users/dongzeyuan1/Desktop/"+student1.getQMNum());
            //   file1.createNewFile();
            //   System.out.println("The txt file "+student1.getQMNum()+".txt is created!");
            e.printStackTrace();
        }
        return student;
    }


    public void studentOutput(Student student) {

        try {
            FileOutputStream fos1 = new FileOutputStream(new File("" + student.getQMNum()));
            ObjectOutputStream oos1;
            oos1 = new ObjectOutputStream(fos1);
            oos1.writeObject(student);
            oos1.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
