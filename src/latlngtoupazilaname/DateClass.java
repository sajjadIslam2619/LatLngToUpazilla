/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package latlngtoupazilaname;

/**
 *
 * @author sajjad
 */
public class DateClass {
    private String year;
    private String month;
    private String day;

    public DateClass() {
    }

    public DateClass(String year, String month, String day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        if(month.equals("Jan")){
            this.month = "01";
        }else if(month.equals("Feb")){
            this.month = "02";
        }else if(month.equals("Mar")){
            this.month = "03";
        }else if(month.equals("Apr")){
            this.month = "04";
        }else if(month.equals("May")){
            this.month = "05";
        }else if(month.equals("Jun")){
            this.month = "06";
        }else if(month.equals("Jul")){
            this.month = "07";
        }else if(month.equals("Aug")){
            this.month = "08";
        }else if(month.equals("Sep")){
            this.month = "09";
        }else if(month.equals("Oct")){
            this.month = "10";
        }else if(month.equals("Nov")){
            this.month = "11";
        }else if(month.equals("Dec")){
            this.month = "12";
        }else{
            this.month = month;
        }
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
    
}
