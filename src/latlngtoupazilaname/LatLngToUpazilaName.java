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
        BufferedWriter bw = null;
        BufferedReader br;
        boolean fileCreator = true;

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
        ArrayList<Integer> expList=new ArrayList<>();
        
        for (int i = 0; i <= 10; i++) {

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
                if (i == 0 && fileCreator == true) {
                    File file = new File("DATA\\mapsGoogleApiContent.txt");
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    fw = new FileWriter(file, true);
                    bw = new BufferedWriter(fw);

                    fileCreator = false;
                }
                if (i >= 0 && i <= 10) {

                    fw.append(i + " "
                            + "DATE" + " "
                            + LISdataPropertyList.get(i).getDate().getYear()
                            + LISdataPropertyList.get(i).getDate().getMonth()
                            + LISdataPropertyList.get(i).getDate().getDay() + " "
                            + "T_LAT" + " "
                            + LISdataPropertyList.get(i).getLatlng().getLat().toString() + " "
                            + "T_LNG " + " "
                            + LISdataPropertyList.get(i).getLatlng().getLng().toString() + " "
                            + "CRACKER" + " "
                            + full + " "
                            + "\n");
                }

                //day 2
                //day 3
                //day 4
                //day 5
                //day 6
                //day 8
                //day 9
                //day 10
                System.out.println(i + " "
                        + "DATE" + " "
                        + LISdataPropertyList.get(i).getDate().getYear()
                        + LISdataPropertyList.get(i).getDate().getMonth()
                        + LISdataPropertyList.get(i).getDate().getDay() + " "
                        + "T_LAT" + " "
                        + LISdataPropertyList.get(i).getLatlng().getLat().toString() + " "
                        + "T_LNG " + " "
                        + LISdataPropertyList.get(i).getLatlng().getLng().toString() + " "
                        + "CRACKER" + " "
                        + full + " "
                        + "\n");
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
                expList.add(i);
                e.printStackTrace();
                continue;
            } catch (IOException e) {
                expList.add(i);
                e.printStackTrace();
                continue;
            }
            //System.out.println("counter:: "+i);
        }
        bw.close();
        System.out.println("#expList:: "+expList);
    }
}
