package tesi;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.text.*;
import java.io.*;

public class report_to_csv {
	private static final String FILENAME = "/Users/valer/Documents/Eclipse/eclipse/ESERCIZI/tesi/src/tesi/report.txt";
	public static String path = "";
	public static String fileName = "";
	public static String toApp = "";
	public static String toSystem = "";
	public static String serial = "";
	public static String encoding = "";
	public static String preskip = "";
	public static String playbackGain = "";
	public static String channel = "";
	public static String originalSampleRate = "";
	public static String PacketDuration = "";
	public static String PacketDuration2 = "";
	public static String PacketDuration3 = "";
	public static String PageDuration = "";
	public static String PageDuration2 = "";
	public static String PageDuration3 = "";
	public static String TotalDataLength = "";
	public static String PlaybackLength = "";
	public static String AverageBitrate = "";
	//public static String CsvFile="/Users/valer/Documents/Eclipse/eclipse/ESERCIZI/tesi/src/tesi/matrice.csv";
	//public static FileWriter writer=new FileWriter(CsvFile);
	public static void main(String[] args) throws FileNotFoundException {

		BufferedReader br = null;
		FileReader fr = null;


		String sCurrentLine = "";
		String [] str_splitted = null;
		boolean endPartition = false;
		PrintWriter pw=null;

		try {

			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);

			//String CsvFile="/Users/valer/Documents/Eclipse/eclipse/ESERCIZI/tesi/src/tesi/matrice.csv";
			//FileWriter writer=new FileWriter(CsvFile);
			pw = new PrintWriter(new File("/Users/valer/Documents/Eclipse/eclipse/ESERCIZI/tesi/src/tesi/matrice.csv"));
			StringBuilder sb = new StringBuilder();
			String ColumnNamesList="nome_file"+";"+"applicazione"+";"+"SistemaOperativo"+";"+"serial"+";"+"encoding"+";"+"preskip"+";"+"playbackGain"+";"+"channel"+";"+"originalSampleRate"+";"+"PacketDuration"+";"+"PacketDuration2"+";"+"PacketDuration3"+";"+"PageDuration"+";"+"PageDuration2"+";"+"PageDuration3"+";"+"TotalDataLength"+";"+"AverageBitrate";
			System.out.println(ColumnNamesList);
			sb.append(ColumnNamesList+"\n");
			pw.write(sb.toString());
			sb = sb.delete(0, sb.length());
			while( (sCurrentLine = br.readLine()) != null ) {

				str_splitted = sCurrentLine.split(" ");
				if(str_splitted[0].equals("Processing")) {   // path - fileName - toApp - toSystem 
					initParam();
					path = str_splitted[2].substring(1, str_splitted[2].length()-4);

					str_splitted = path.split("/");
					fileName = str_splitted[str_splitted.length-1];
					if(path.contains("Whatsapp") || path.contains("whatsapp"))
					{
						//System.out.println(" , W");
						//Whatsapp
						toApp="W";
					}
					if(path.contains("Telegram")|| path.contains("telegram"))
					{
						//System.out.println(" , T");
						//Telegram
						toApp="T";
					}
					else if(path.contains("FB"))
						toApp="F";
					//PC,WP o Android?
					if(path.contains("wp")|| path.contains("WP"))
						toSystem="WP";
					else if(path.contains("pc")|| path.contains("web")|| path.contains("app_desktop"))
						toSystem="PC";
					else if(path.contains("android")|| path.contains("Android")|| path.contains("ANDROID"))
						toSystem="A";
					if(path.contains("registrato_su_android_E_inoltrato_su_wp"))
						toSystem="A";

					if(path.contains("registrato_da_android_ricevuto_e_inoltrato_da_wp"))
						toSystem="A";
					if(path.contains("registro_su_wb_invio_ad_android_e_inoltro_a_wp"))
						toSystem="PC";

					if(path.contains("Canzone/Nuova_cartella/Nuova_cartella/AUD-20170409-WA0001.waptt"))
						toSystem="WP";
					if(path.contains("/Canzone/Nuova_cartella/AUD-20170409-WA0002.waptt"))
						toSystem="WP";
				}
				else if(str_splitted[0].equals("New")) {  // serial
						serial = str_splitted[5].substring(0,str_splitted[5].length()-2);
					}
				else if(str_splitted[0].equals("Encoded")) { // encoding
							encoding = str_splitted[2];
						}
				else if(str_splitted[0].equals("	Pre-skip:")) { // preskip
								preskip = str_splitted[1];
							}
				else if(str_splitted[0].equals("	Playback") && str_splitted[1].equals("gain:")) {  // playbackGain
									playbackGain = str_splitted[2];
								}
				else if(str_splitted[0].equals("	Channels:")) {  // channel
										channel = str_splitted[1];
									}
				else if(str_splitted[0].equals("	Original") && str_splitted[1].equals("sample")) {  // originalSampleRate
											originalSampleRate = str_splitted[3].substring(0,str_splitted[3].length()-2);

										}
				else if(str_splitted[0].equals("	Packet") && str_splitted[1].equals("duration:")) {  //packetDuration
												String tmpInit= "";
												String tmp= "";
												for(int i=2; i<str_splitted.length; i++)  // Riaccorpo la stringa splittata escludendo "Packet duration:"
													tmpInit += str_splitted[i];
												for(int i=0; i<tmpInit.length(); i++)  // Tolgo gli spazi dalla stringa accorpata precedentemente
													if(tmpInit.charAt(i) != ' ')
														tmp += tmpInit.charAt(i);
												str_splitted = tmp.split(",");
												//PacketDuration = str_splitted[0].substring(0,str_splitted[0].length()-7)+","+str_splitted[1].substring(0,str_splitted[1].length()-7)+","+str_splitted[2].substring(0,str_splitted[2].length()-7);
												PacketDuration = str_splitted[0].substring(0,str_splitted[0].length()-7);
														PacketDuration2=str_splitted[1].substring(0,str_splitted[1].length()-7);
														PacketDuration3=str_splitted[2].substring(0,str_splitted[2].length()-7);
												//NO PacketDuration =str_splitted[2].substring(0,str_splitted[3].length());
											}
				else if(str_splitted[0].equals("	Page") && str_splitted[1].equals("duration:")) {  //pageDuration
													String tmpInit= "";
													String tmp= "";
													for(int i=2; i<str_splitted.length; i++)  // Riaccorpo la stringa splittata escludendo "Page duration:"
														tmpInit += str_splitted[i];
													for(int i=0; i<tmpInit.length(); i++)  // Tolgo gli spazi dalla stringa accorpata precedentemente
														if(tmpInit.charAt(i) != ' ')
															tmp += tmpInit.charAt(i);
													str_splitted = tmp.split(",");
													//PageDuration = str_splitted[0].substring(0,str_splitted[0].length()-7)+","+str_splitted[1].substring(0,str_splitted[1].length()-7)+","+str_splitted[2].substring(0,str_splitted[2].length()-7);
													PageDuration = str_splitted[0].substring(0,str_splitted[0].length()-7);
													PageDuration2 = str_splitted[1].substring(0,str_splitted[1].length()-7);
													PageDuration3 =str_splitted[2].substring(0,str_splitted[2].length()-7);
				}
				else if(str_splitted[0].equals("	Total") && str_splitted[1].equals("data")) {  // TotalDataLength
														TotalDataLength= str_splitted[3];

													}
				else if(str_splitted[0].equals("	Playback") && str_splitted[1].equals("length:")) {  // PlaybackLength
															PlaybackLength = str_splitted[2];
														}
				else if(str_splitted[0].equals("	Average") ){  // AverageBitrate
																AverageBitrate= str_splitted[2];
																endPartition = true;
															}
				if (encoding.contains("WhatsApp"))
					toApp="W";


				if(endPartition) {  //stampa solo dopo ogni ricorrenza di file.
					//sb.append(ColumnNamesList+"\n");
					sb.append(fileName);
					sb.append(';');
					sb.append(toApp);
					sb.append(';');
					sb.append(toSystem);
					sb.append(';');
					sb.append(serial);
					sb.append(';');
					sb.append(encoding);
					sb.append(';');
					sb.append(preskip);
					sb.append(';');
					sb.append(playbackGain);
					sb.append(';');
					sb.append(channel);
					sb.append(';');
					sb.append(originalSampleRate);
					sb.append(';');
					sb.append(PacketDuration);
					sb.append(';');
					sb.append(PacketDuration2);
					sb.append(';');
					sb.append(PacketDuration3);
					sb.append(';');
					sb.append(PageDuration);
					sb.append(';');
					sb.append(PageDuration2);
					sb.append(';');
					sb.append(PageDuration3);
					sb.append(';');
					sb.append(TotalDataLength);
					sb.append(';');
					//sb.append(PlaybackLength);
					//sb.append(';');
					sb.append(AverageBitrate);
					sb.append('\n');
					pw.write(sb.toString());
					sb = sb.delete(0, sb.length());
					printParam();
					endPartition = false;



					//}

				}
				//endPartition = false;
			}
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
		finally {
			try {

				if (br != null)
				{
					br.close();
					pw.flush();
					pw.close();
				}
				if (fr != null)
				{fr.close();
				pw.flush();
				pw.close();}

			} catch (IOException ex) {

				ex.printStackTrace();
			}
		}

	}

	public static void initParam() {
		path = "N/D";
		fileName = "N/D";
		toApp = "N/D";
		toSystem = "N/D";
		serial = "N/D";
		encoding = "N/D";
		preskip = "N/D";
		playbackGain = "N/D";
		channel = "N/D";
		originalSampleRate = "N/D";
		PacketDuration = "N/D";
		PacketDuration2 = "N/D";
		PacketDuration3 = "N/D";
		PageDuration = "N/D";
		PageDuration2 = "N/D";
		PageDuration3 = "N/D";
		TotalDataLength = "N/D";
		PlaybackLength = "N/D";
		AverageBitrate = "N/D";
	}

	public static void printParam() throws IOException {
		//System.out.println(fileName+"	"+toApp+","+toSystem+","+serial+","+encoding+","+preskip+","+playbackGain+","+channel+","+originalSampleRate+","+PacketDuration+","+PageDuration+","+TotalDataLength+","+PlaybackLength+","+AverageBitrate);
		System.out.println(toApp+","+toSystem+","+serial+","+encoding+","+preskip+","+playbackGain+","+channel+","+originalSampleRate+","+PacketDuration+","+PageDuration+","+TotalDataLength+","+PlaybackLength+","+AverageBitrate);



	}
}
