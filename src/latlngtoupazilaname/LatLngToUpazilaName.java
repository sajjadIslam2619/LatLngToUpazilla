package latlngtoupazilaname;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 *
 * @author sajjad
 */
public class LatLngToUpazilaName {

    public static ArrayList<LISdataProperty> LISdataPropertyList = new ArrayList<LISdataProperty>();
    String upazilaName = null;

    public static void main(String[] args) throws IOException, InterruptedException {
        // TODO code application logic here

        LatLngToUpazilaName latLngToUpazilaName = new LatLngToUpazilaName();

        latLngToUpazilaName.LISdataFileRead();
        latLngToUpazilaName.findLocationNameFromApi();

//        for(int i=0;i<LISdataPropertyList.size();i++){
//            System.out.println(" "+LISdataPropertyList.get(i).getRadiance());
//        }
    }

    private void LISdataFileRead() throws FileNotFoundException, IOException {

        File LIS_dir = new File(FilePath.LISDataFileDir);
        File[] LIS_files = LIS_dir.listFiles();

        for (File f : LIS_files) {
            if (f.isFile()) {
                //System.out.println("file  " + j + " ");
                BufferedReader inputStream = null;
                try {
                    inputStream = new BufferedReader(new FileReader(f));
                    inputStream.readLine();// skip first line of each file ...
                    String line;

                    while ((line = inputStream.readLine()) != null) {
                        LISdataProperty lISdataProperty_obj = new LISdataProperty();
                        LatLng latlng = new LatLng();
                        DateClass dateClass = new DateClass();
                        //System.out.println("  "+ i +"  "+line);
                        String[] lineSplit = line.split("\\s+"); // remove multiple space using '\\s+' 
                        //System.out.println(" "+lineSplit[0].trim().substring(0, 4));
                        dateClass.setYear(lineSplit[0].trim().substring(0, 4).trim());
                        //System.out.println(" "+lineSplit[1].trim().replace("[", ""));
                        dateClass.setMonth(lineSplit[1].trim().replace("[", "").trim());
                        //System.out.println(" "+lineSplit[2].trim().replace("]", ""));
                        dateClass.setDay(lineSplit[2].trim().replace("]", "").trim());
                            //System.out.println(" "+lineSplit[3].trim());// print '(' 
                        //System.out.println(" "+lineSplit[4].trim().replace(",", ""));
                        latlng.setLat(Float.parseFloat(lineSplit[4].trim().replace(",", "").trim()));
                        //System.out.println(" "+lineSplit[5].trim().replace(")", ""));
                        latlng.setLng(Float.parseFloat(lineSplit[5].trim().replace(")", "").trim()));
                        //System.out.println(" "+lineSplit[6].trim());
                        lISdataProperty_obj.setRadiance(lineSplit[6].trim());
                        //System.out.println(" "+lineSplit[7].trim());
                        lISdataProperty_obj.setMilliseconds(lineSplit[7].trim());
                        //System.out.println(" "+lineSplit[8].trim());//groups not necessary 
                        //System.out.println(" "+lineSplit[9].trim());
                        lISdataProperty_obj.setEvents(lineSplit[9].trim());
                        //System.out.println(" "+lineSplit[10].trim());// orbit-Id not necessary 
                        lISdataProperty_obj.setDate(dateClass);
                        lISdataProperty_obj.setLatlng(latlng);
                        LISdataPropertyList.add(lISdataProperty_obj);
                    }
                } finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                }
            }

        }
    }

    private void findLocationNameFromApi() throws IOException, InterruptedException {

        FileWriter fw = null;

        BufferedWriter bw_1 = null;
        BufferedWriter bw_2 = null;
        BufferedWriter bw_3 = null;
        BufferedWriter bw_4 = null;
        BufferedWriter bw_5 = null;
        BufferedWriter bw_6 = null;
        BufferedWriter bw_7 = null;
        BufferedWriter bw_8 = null;
        BufferedWriter bw_9 = null;
        BufferedWriter bw_10 = null;
        BufferedWriter bw_11 = null;
        BufferedWriter bw_12 = null;

        BufferedReader br;

         //write on file...
//        File file = new File(FilePath.mapsGoogleApiContentFilePath);
//        if (!file.exists()) {
//            file.createNewFile();
//        }
//        fw = new FileWriter(file, true);
//        bw = new BufferedWriter(fw);
        //fw=new FileWriter(FilePath.LISDataWithUpazilaFilePath);
        //GeoApiContext context = new GeoApiContext().setApiKey("AIza...");
        boolean fileCreator_1 = true;
        boolean fileCreator_2 = true;
        boolean fileCreator_3 = true;
        boolean fileCreator_4 = true;
        boolean fileCreator_5 = true;
        boolean fileCreator_6 = true;
        boolean fileCreator_7 = true;
        boolean fileCreator_8 = true;
        boolean fileCreator_9 = true;
        boolean fileCreator_10 = true;

        double startTime = 0;
        double endTime = 0;
        double time = 0;
        double totalTime = 0;
        int itetaratorChecker = 0;//this is to check if there is 10 call within a second,
        //because if there is 10 call with in a second maps.googleapi will return "OVER_QUERY_LIMIT"

        System.out.println("total size " + LISdataPropertyList.size());
        
         //year 2011-2012
        
        //total size ==22554
        
        /*
        day 1.. 0-2400,
        day 2.. 2401-4800,
        day 3.. 4801-7200,
        day 4.. 7201-9600,
        day 5.. 9601-12000,
        day 6.. 12001-14400,
        day 7.. 14401-16800,
        day 8.. 16801-19200,
        day 9.. 19201-21600,
        day 10..21601-22554
        */
        
        /*
        
        change the loop counter here ... 
        
        */
        for (int i = 0; i <= 2400; i++) {

            startTime = System.nanoTime();
            /*
            change the API-KEY ... just the string after "key="...
            */
            String urlSpaceFree = "https://maps.googleapis.com/maps/api/geocode/json?latlng="
                    + LISdataPropertyList.get(i).getLatlng().getLat().toString() + ","
                    + LISdataPropertyList.get(i).getLatlng().getLng().toString() + "&" + "key=" + "AIzaSyAs4OV0Yu7b4V-5eFE70Ymyow-wRKqRJ64";
            //+"&"+"key="+"AIzaSyAs4OV0Yu7b4V-5eFE70Ymyow-wRKqRJ64"
            
            String mainUrl = urlSpaceFree.replaceAll(" ", "%20");
            try {
                URL url = new URL(mainUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept", "application/json");

                if (conn.getResponseCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
                }
                br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

                String output = "", full = "";
                while ((output = br.readLine()) != null) {
                    // System.out.println(output);
                    full += output;
                }
                // day 1
                if (i == 0 && fileCreator_1 == true) {
                    File file = new File("DATA\\mapsGoogleApiContent_1.txt");
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    fw = new FileWriter(file, true);
                    bw_1 = new BufferedWriter(fw);

                    fileCreator_1 = false;
                }
                if (i >= 0 && i <= 2400) {

                    fw.append(i + " "+
                            "DATE"+" "+
                            LISdataPropertyList.get(i).getDate().getYear()+
                            LISdataPropertyList.get(i).getDate().getMonth()+
                            LISdataPropertyList.get(i).getDate().getDay()+" "+
                            "T_LAT" + " " + 
                            LISdataPropertyList.get(i).getLatlng().getLat().toString() + " "+ 
                            "T_LNG " + " " + 
                            LISdataPropertyList.get(i).getLatlng().getLng().toString() + " " + 
                            "CRACKER" +" " + 
                            full + " " + 
                            "\n");
                }
                //day 2
                if (i == 2401 && fileCreator_2 == true) {
                    File file = new File("DATA\\mapsGoogleApiContent_2.txt");
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    fw = new FileWriter(file, true);
                    bw_2 = new BufferedWriter(fw);

                    fileCreator_2 = false;
                }
                if (i >= 2401 && i <= 4800) {
                    fw.append(i + " "+
                            "DATE"+" "+
                            LISdataPropertyList.get(i).getDate().getYear()+
                            LISdataPropertyList.get(i).getDate().getMonth()+
                            LISdataPropertyList.get(i).getDate().getDay()+" "+
                            "T_LAT" + " " + 
                            LISdataPropertyList.get(i).getLatlng().getLat().toString() + " "+ 
                            "T_LNG " + " " + 
                            LISdataPropertyList.get(i).getLatlng().getLng().toString() + " " + 
                            "CRACKER" +" " + 
                            full + " " + 
                            "\n");
                }
                
                //day 3
                
                if (i == 4801 && fileCreator_3 == true) {
                    File file = new File("DATA\\mapsGoogleApiContent_3.txt");
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    fw = new FileWriter(file, true);
                    bw_3 = new BufferedWriter(fw);

                    fileCreator_3 = false;
                }
                if (i >= 4801 && i <= 7200) {

                    fw.append(i + " "+
                            "DATE"+" "+
                            LISdataPropertyList.get(i).getDate().getYear()+
                            LISdataPropertyList.get(i).getDate().getMonth()+
                            LISdataPropertyList.get(i).getDate().getDay()+" "+
                            "T_LAT" + " " + 
                            LISdataPropertyList.get(i).getLatlng().getLat().toString() + " "+ 
                            "T_LNG " + " " + 
                            LISdataPropertyList.get(i).getLatlng().getLng().toString() + " " + 
                            "CRACKER" +" " + 
                            full + " " + 
                            "\n");
                }

                //day 4
                if (i == 7201 && fileCreator_4 == true) {
                    File file = new File("DATA\\mapsGoogleApiContent_4.txt");
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    fw = new FileWriter(file, true);
                    bw_4 = new BufferedWriter(fw);

                    fileCreator_4 = false;
                }
                if (i >= 7201 && i <= 9600) {

                    fw.append(i + " "+
                            "DATE"+" "+
                            LISdataPropertyList.get(i).getDate().getYear()+
                            LISdataPropertyList.get(i).getDate().getMonth()+
                            LISdataPropertyList.get(i).getDate().getDay()+" "+
                            "T_LAT" + " " + 
                            LISdataPropertyList.get(i).getLatlng().getLat().toString() + " "+ 
                            "T_LNG " + " " + 
                            LISdataPropertyList.get(i).getLatlng().getLng().toString() + " " + 
                            "CRACKER" +" " + 
                            full + " " + 
                            "\n");
                }
                //day 5
                if (i == 9601 && fileCreator_5 == true) {
                    File file = new File("DATA\\mapsGoogleApiContent_5.txt");
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    fw = new FileWriter(file, true);
                    bw_5 = new BufferedWriter(fw);

                    fileCreator_5 = false;
                }
                if (i >= 9601 && i <= 12000) {

                    fw.append(i + " "+
                            "DATE"+" "+
                            LISdataPropertyList.get(i).getDate().getYear()+
                            LISdataPropertyList.get(i).getDate().getMonth()+
                            LISdataPropertyList.get(i).getDate().getDay()+" "+
                            "T_LAT" + " " + 
                            LISdataPropertyList.get(i).getLatlng().getLat().toString() + " "+ 
                            "T_LNG " + " " + 
                            LISdataPropertyList.get(i).getLatlng().getLng().toString() + " " + 
                            "CRACKER" +" " + 
                            full + " " + 
                            "\n");
                }
                
                // day 6

                if (i == 12001 && fileCreator_6 == true) {
                    File file = new File("DATA\\mapsGoogleApiContent_6.txt");
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    fw = new FileWriter(file, true);
                    ;
                    bw_6 = new BufferedWriter(fw);

                    fileCreator_6 = false;
                }
                if (i >= 12001 && i <= 14400) {

                    fw.append(i + " "+
                            "DATE"+" "+
                            LISdataPropertyList.get(i).getDate().getYear()+
                            LISdataPropertyList.get(i).getDate().getMonth()+
                            LISdataPropertyList.get(i).getDate().getDay()+" "+
                            "T_LAT" + " " + 
                            LISdataPropertyList.get(i).getLatlng().getLat().toString() + " "+ 
                            "T_LNG " + " " + 
                            LISdataPropertyList.get(i).getLatlng().getLng().toString() + " " + 
                            "CRACKER" +" " + 
                            full + " " + 
                            "\n");
                }
                //day 7
                
                if (i == 14401 && fileCreator_7 == true) {
                    File file = new File("DATA\\mapsGoogleApiContent_7.txt");
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    fw = new FileWriter(file, true);
                    bw_7 = new BufferedWriter(fw);

                    fileCreator_7 = false;
                }
                if (i >= 14401 && i <= 16800) {

                    fw.append(i + " "+
                            "DATE"+" "+
                            LISdataPropertyList.get(i).getDate().getYear()+
                            LISdataPropertyList.get(i).getDate().getMonth()+
                            LISdataPropertyList.get(i).getDate().getDay()+" "+
                            "T_LAT" + " " + 
                            LISdataPropertyList.get(i).getLatlng().getLat().toString() + " "+ 
                            "T_LNG " + " " + 
                            LISdataPropertyList.get(i).getLatlng().getLng().toString() + " " + 
                            "CRACKER" +" " + 
                            full + " " + 
                            "\n");
                }
                
                //day 8

                if (i == 16801 && fileCreator_8 == true) {
                    File file = new File("DATA\\mapsGoogleApiContent_8.txt");
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    fw = new FileWriter(file, true);
                    bw_8 = new BufferedWriter(fw);

                    fileCreator_8 = false;
                }
                if (i >= 16801 && i <= 19200) {

                    fw.append(i + " "+
                            "DATE"+" "+
                            LISdataPropertyList.get(i).getDate().getYear()+
                            LISdataPropertyList.get(i).getDate().getMonth()+
                            LISdataPropertyList.get(i).getDate().getDay()+" "+
                            "T_LAT" + " " + 
                            LISdataPropertyList.get(i).getLatlng().getLat().toString() + " "+ 
                            "T_LNG " + " " + 
                            LISdataPropertyList.get(i).getLatlng().getLng().toString() + " " + 
                            "CRACKER" +" " + 
                            full + " " + 
                            "\n");
                }
                //day 9
                if (i == 19201 && fileCreator_9 == true) {
                    File file = new File("DATA\\mapsGoogleApiContent_9.txt");
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    fw = new FileWriter(file, true);
                    bw_9 = new BufferedWriter(fw);

                    fileCreator_9 = false;
                }
                if (i >= 19201 && i <= 21600) {

                    fw.append(i + " "+
                            "DATE"+" "+
                            LISdataPropertyList.get(i).getDate().getYear()+
                            LISdataPropertyList.get(i).getDate().getMonth()+
                            LISdataPropertyList.get(i).getDate().getDay()+" "+
                            "T_LAT" + " " + 
                            LISdataPropertyList.get(i).getLatlng().getLat().toString() + " "+ 
                            "T_LNG " + " " + 
                            LISdataPropertyList.get(i).getLatlng().getLng().toString() + " " + 
                            "CRACKER" +" " + 
                            full + " " + 
                            "\n");
                }
                //day 10
                if (i == 21601 && fileCreator_10 == true) {
                    File file = new File("DATA\\mapsGoogleApiContent_10.txt");
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    fw = new FileWriter(file, true);
                    bw_10 = new BufferedWriter(fw);

                    fileCreator_10 = false;
                }
                if (i >= 21601 && i <= 22554) {

                    fw.append(i + " "+
                            "DATE"+" "+
                            LISdataPropertyList.get(i).getDate().getYear()+
                            LISdataPropertyList.get(i).getDate().getMonth()+
                            LISdataPropertyList.get(i).getDate().getDay()+" "+
                            "T_LAT" + " " + 
                            LISdataPropertyList.get(i).getLatlng().getLat().toString() + " "+ 
                            "T_LNG " + " " + 
                            LISdataPropertyList.get(i).getLatlng().getLng().toString() + " " + 
                            "CRACKER" +" " + 
                            full + " " + 
                            "\n");
                }

                System.out.println(i + " "+
                            "DATE"+" "+
                            LISdataPropertyList.get(i).getDate().getYear()+
                            LISdataPropertyList.get(i).getDate().getMonth()+
                            LISdataPropertyList.get(i).getDate().getDay()+" "+
                            "T_LAT" + " " + 
                            LISdataPropertyList.get(i).getLatlng().getLat().toString() + " "+ 
                            "T_LNG " + " " + 
                            LISdataPropertyList.get(i).getLatlng().getLng().toString() + " " + 
                            "CRACKER" +" " + 
                            full + " " + 
                            "\n");
                // checking for request send per second...
                
                itetaratorChecker++;
                endTime = System.nanoTime();
                time = (endTime - startTime) / 1000000000.0;
                totalTime += time;

                //for safety 
                if (itetaratorChecker == 5 || itetaratorChecker == 10) {
                    System.out.println("sleeping ");
                    Thread.sleep(500);
                }
                if (itetaratorChecker > 10) {
                    if (totalTime <= 1.0) {
                        double sleepTime = 1.0 - totalTime;
                        System.out.println("sleepTime " + sleepTime);
                        long sleepTimeInMillsec = (long) sleepTime * 1000;
                        System.out.println("sleepTimeInMillsec " + sleepTimeInMillsec);
                        Thread.sleep(sleepTimeInMillsec);
                    }
                    totalTime = 0;
                    itetaratorChecker = 0;
                }

                startTime = 0;
                endTime = 0;
                time = 0;

                conn.disconnect();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //System.out.println("counter:: "+i);
        }
        bw_1.close();
        bw_2.close();
        bw_3.close();
        bw_4.close();
        bw_5.close();
        bw_6.close();
        bw_7.close();
        bw_8.close();
        bw_9.close();
        bw_10.close();

    }
}
