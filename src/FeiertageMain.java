import java.io.File;
import java.io.FileNotFoundException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class FeiertageMain {
	
	static int[] i= new int[5];
	static int years=10;

    public static void main(String[] args) {
        ArrayList<LocalDate> dates = read();
        
        calculate(dates,years);
        ausgabe();
    }

    private static ArrayList<LocalDate> read(){
        ArrayList<LocalDate> dates=new ArrayList<>();
        try {
            File data = new File("C:\\Users\\Konrad\\Documents\\4. Klasse\\SwpDateien\\data");
            Scanner scanner = new Scanner(data);
            while(scanner.hasNextLine()){
                String s=scanner.nextLine();
                int d=Integer.parseInt(s.substring(0,s.indexOf(".")));
                s=s.substring(s.indexOf(".")+1);
                int m=Integer.parseInt(s.substring(0,s.indexOf(".")));
                s=s.substring(s.indexOf(".")+1);
                int year=Integer.parseInt(s);
                dates.add(LocalDate.of(year,m,d));
            }
            scanner.close();
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        return dates;
    }

    private static void calculate(ArrayList<LocalDate> dates,int years){
        
        for(LocalDate ld:dates){
            for(int j=0;j<years;j++){
                LocalDate date= LocalDate.of(ld.getYear()+j,ld.getMonth(),ld.getDayOfMonth());
                switch(date.getDayOfWeek()) {
                    case MONDAY:
                        i[0]++;
                        break;
                    case TUESDAY:
                        i[1]++;
                        break;
                    case WEDNESDAY:
                        i[2]++;
                        break;
                    case THURSDAY:
                        i[3]++;
                        break;
                    case FRIDAY:
                        i[4]++;
                        break;
				default:
					break;
                }
            }
        }
        
    }
    
    private static void ausgabe()
    {
    	//Ostermontag,Pfingstmontag
        i[0]+=2*years;
        //Fronleichnam, Christihimmelfahrt
        i[3]+=2*years;
        System.out.printf("MO:%2d DI:%2d MI:%2d DO:%2d FR:%2d",i[0],i[1],i[2],i[3],i[4]);
        System.out.println();
    }
}
