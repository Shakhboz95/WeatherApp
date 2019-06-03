package weather.uz.weatherapp;


public class URL {
    public static String APPID="8259db18c8cc6b2e5de089f2d2648a8c";
    public static String BaseUrl="http://api.openweathermap.org/data/2.5/";

    public  static int Tashkent = 1512569,Moscow = 524901, Astana = 1526273,London = 2643743,Australia = 2147714,Amsterdam = 2759794;
    public static int Beijing = 1816670,Tokyo = 1850147,Dubai = 292223, Madrid = 3117735,Paris = 2968815, Berlin = 2950159,Istanbul = 745044;
    public static int[] cities = new int[]{Tashkent,Moscow,Astana,Amsterdam,London,Australia,Beijing,Berlin,Istanbul,Paris,Madrid
            ,Dubai,Tokyo};

    public static  String getGroupCityUrl(){
        String groupUrl="";
        StringBuilder sb = new StringBuilder();
        String space = "";
        for (int city=0;city<cities.length;city++) {
            sb.append(space).append(cities[city]);
            space = ",";
        }

        groupUrl = "group?id=" + sb.toString()+"&APPID="+APPID+"&units=metric";

        return groupUrl;
    }
    public static String getWeatherDetails(int cityId){
        String weatherUrl = "weather?id=" + cityId+"&APPID="+APPID+"&units=metric";
        return weatherUrl;
    }



}
