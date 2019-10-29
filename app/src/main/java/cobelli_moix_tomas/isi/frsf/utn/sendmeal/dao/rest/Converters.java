package cobelli_moix_tomas.isi.frsf.utn.sendmeal.dao.rest;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cobelli_moix_tomas.isi.frsf.utn.sendmeal.domain.ItemsPedido;

public class Converters {
    @TypeConverter
    public static Date fromTimestamp(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static List<ItemsPedido> fromString(String value) {
        Type listType = new TypeToken<List<ItemsPedido>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromList(List<ItemsPedido> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }

    /*@TypeConverter
    public static String GetEnumDescription(Enum value) {
        FieldInfo fi= value.GetType().GetField(value.ToString());
        DescriptionAttribute[] attributes = (DescriptionAttribute[])fi.GetCustomAttributes(typeof(DescriptionAttribute), false);
        return (attributes.Length>0)?attributes[0].Description:value.ToString();
    }

    @TypeConverter
    public static string GetEnumName(System.Type value, string description) {
        FieldInfo [] fis = value.GetFields();
        foreach(FieldInfo fi in fis) {
            DescriptionAttribute [] attributes = (DescriptionAttribute[])fi.GetCustomAttributes(typeof(DescriptionAttribute), false);
            if(attributes.Length>0) {
                if(attributes[0].Description == description) {
                    return fi.Name;
                }
            }
        }
        return description;
    }*/
}