package Week03;

public class Student_Lab {
	public String fullName;
	public int[] grades;

	public Student_Lab(String fName,int[] grades) { 
	   this.fullName = fName; 
	   this.grades = grades; 
	}  
		
   public void describe() { 
      System.out.println("Student: " + this.fullName); 
      System.out.println("Grades:"); 
      for (int grade : this.grades) { 
    	  System.out.println("\t" + grade + " "); 
      } 
      System.out.println(); 
   }
}