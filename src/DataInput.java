import java.util.Scanner;

public class DataInput {
    Scanner sc = new Scanner(System.in);

   public static Student[] StudentArray = new Student[18];
   public static Student[] sectionOne = new Student[9];
   public static Student[] sectionTwo = new Student[9];




    //public static int section01 = StudentArray.length/2 ;
    int input = 0;


    public void gettingInput(String value) {
        System.out.println(value);
    }

    void printName(String name){
        int x = 0;
        while(x < input){
            System.out.println(x + "\n" + StudentArray[x].toString());
            x++;
        }
    }

    boolean checkStudent(){
        int x = 0;
        boolean bool = false;
        if ( StudentArray.length != x && sectionOne.length != x && sectionTwo.length != x) {
            bool = true;
        }
        return bool;
    }


    //public void addRecord(name, sid, email, password){
    public void addRecord(Student student){
        if(input < StudentArray.length){
            StudentArray[input] = student;

        }
        input++;
    }

    public void addToSec01(Student student){



        if(input < StudentArray.length){
            //sectionOne[input] = student;

            try {
                if (StudentArray[input].email == student.email && StudentArray[input].password == student.password) {


                    sectionOne[input].email = StudentArray[input].email;
                    sectionOne[input].password = StudentArray[input].password;
                    sectionOne[input].name = StudentArray[input].name;
                    sectionOne[input].sid = StudentArray[input].sid;
                }
            } finally {
                System.out.println("");
            }
//        if(input < sectionOne.length){
//           try {
//               for (int i = 0; i <sectionOne.length; i++){
//                try {
//                    if (StudentArray[i].email == email){
//                                sectionOne[i].email = StudentArray[i].email;
//                                sectionOne[i].password = StudentArray[i].password;
//                                sectionOne[i].name = StudentArray[i].name;
//                                sectionOne[i].sid = StudentArray[i].sid;
//                    }
//                } catch (Exception e) {
//                    continue;
//                }
//               }
//           } catch (Exception e) {
//               e.printStackTrace();
//           }
//
//           //  sectionOne[input] = student;
//        }
        input++;
    }

//    public void addToSec02(Student student){
//        if(input < sectionTwo.length){
//            sectionTwo[input] = student;
//        }
//        input++;
//    }
}
}
