package angela.example.parkingmacedonia;


public class Reservation {
    private String userName;
    private String cityName;
    private String parkingLotName;
    private String date;
    private String time;


    public Reservation(String userName, String cityName, String parkingLotName, String date, String time) {
        this.userName = userName;
        this.cityName = cityName;
        this.parkingLotName = parkingLotName;
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "cityName='" + cityName + '\'' +
                ", parkingLotName='" + parkingLotName + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", username='" + userName +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }



    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getParkingLotName() {
        return parkingLotName;
    }

    public void setParkingLotName(String parkingLotName) {
        this.parkingLotName = parkingLotName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}