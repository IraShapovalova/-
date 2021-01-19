package FluteDilet;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;
//библиотеки для работы с файлами
import java.io.File;
import java.util.*;

//подключаем библиотеки



public class FluteDilet6 {

    private MidiChannel[] channels = null;
    private Synthesizer synth = null;

   public FluteDilet6() {
        try {
            synth = MidiSystem.getSynthesizer();
            synth.open();
            channels = synth.getChannels();
           // channels[0].programChange(41);//инструмент - скрипка
        } catch (MidiUnavailableException ex) {
            Logger.getLogger(FluteDilet6.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void close() {
        synth.close();
    }

    public void playSound(int channel, int duration, int volume,int instr, int... notes) {
    	//Устанавливаем параметры канал, длительность, громкость, номер инструмента, номер ноты
       for (int note : notes) {
    	   channels[channel].programChange(instr);
            channels[channel].noteOn(note, volume);
        }
        try {
            Thread.sleep(duration);
        } catch (InterruptedException ex) {
            Logger.getLogger(FluteDilet6.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int note : notes) {
            channels[channel].noteOff(note);
        }
    }
	
	
	
	//начало раздела оснавной программы
    
	public static void main(String[] args) throws MidiUnavailableException, Exception {

		//получи необходимые нам данные из текстового файла
		
		//
		
		
		
		 // передать путь к файлу в качестве параметра

	    File file = new File("C:\\Proekt\\001.txt");

	     try (Scanner sc = new Scanner(file)) {
	    	char a = ' ';
	    	String str = null;
	    	String gromstr = "";
	    	String kanalstr = "";
	    	String instr = "";
	    	String notqstr = "";
	    	String dlitstr = "";
	    	
	    	String delimeter = "";
	    	
	    	int [][] notes; 
	        int SCh = 0;
	        List<String[]> A = new ArrayList<String[]>();
	        
			while (sc.hasNextLine()) {

				//SCh++;
				
				str = String.valueOf(sc.nextLine());
				a = str.charAt(0);
				if(a=='g') {
					for(int i=1; i<= str.length()-1; i++) {
						gromstr = gromstr + String.valueOf(str.charAt(i));
					}
				}
				if(a=='k') {
					for(int i=1; i<= str.length()-1; i++) {
						kanalstr = kanalstr + String.valueOf(str.charAt(i));
					}
				}
				if(a=='i') {
					for(int i=1; i<= str.length()-1; i++) {
						instr = instr + String.valueOf(str.charAt(i));
					}
				}
				if(a == '0' || a == '1' ||a == '2' ||a == '3' ||a == '4' ||a == '5' ||a == '6' ||a == '7' ||a == '8' ||a == '9' ) {
				      delimeter = "\\,"; // Разделитель
				      String[] subStr = str.split(delimeter);  //создание разделённой строки
				      // Вывод результата
				      for(int ii = 0; ii < subStr.length; ii++) {
				         //System.out.println(subStr[ii]);
				    	  	if (ii == 0) 
				    	  	{
				    	  		dlitstr = subStr[ii];
				    	  	} 
				    	  	else
				    	  	{
				    	  		 notqstr = subStr[ii];
				    	  	}
	     		      }
					    A.add(new String[] {kanalstr,dlitstr,gromstr,instr,notqstr});
				}
			
			}
			
			//System.out.println(gromstr); 
			//System.out.println(kanalstr);
			//System.out.println(instr);
			
			
		    for (String[] row : A) {
		    	//System.out.println(row[0]+""+row[1]+""+row[2]+""+row[3]+""+row[4]);
		    	SCh++;
		    }
		    
		    notes = new int[SCh][];
		    SCh = 0;
		    for (String[] row : A) {
		    	notes[SCh] = new int[]{Integer.parseInt(row[0]), Integer.parseInt(row[1]), Integer.parseInt(row[2]), Integer.parseInt(row[3]), Integer.parseInt(row[4].trim())};
		    SCh++;
		    }
		
		
		
		
		
		
		
		
		//int grom = 80;
		//int kanal = 0;
		//int instr = 57;
		//Канал(0), длительность(1), громкость(2), номер инструмента(3), номер ноты(4)
		//int notes[][] = {{kanal,470, grom, instr,81}, {kanal,230,grom, instr,80}, {kanal,470,grom, instr,81}, {kanal,250,grom, instr,-1}, {kanal,230,grom, instr,80}, {kanal,470,grom, instr,81}, {kanal,230,grom, instr,69}, {kanal,230,grom, instr,76}, {kanal,470,grom, instr,81}};
		
		
		FluteDilet6 player = new FluteDilet6();
	
		for (int[] note : notes) {
			 if (note[4] != -1) {
				 player.playSound(note[0], note[1], note[2],note[3], note[4]);
				 }
			 else {
				 try {
	                    Thread.sleep(note[1]);
	                } catch (InterruptedException ex) {
	                    Logger.getLogger(FluteDilet6.class.getName()).log(Level.SEVERE, null, ex);
	                }
			 }
		}
		 
		 player.close();
		 
	}
}
}





