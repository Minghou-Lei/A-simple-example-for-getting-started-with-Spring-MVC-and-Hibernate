package BookStore.util;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Deprecated
public class DBRsHelp<T> {
	public List<T> util(T t, ResultSet rs) throws Exception{
		List<T> list = new ArrayList<>();
		Class c = t.getClass();
		Field[] fs = c.getDeclaredFields();
		if(rs!=null){
			while (rs.next()){
				t = (T)c.newInstance();
				for(int i=0;i<fs.length;i++){
					Field f = t.getClass().getDeclaredField(fs[i].getName());
					f.setAccessible(true);
					if(f.getType().getName().equals(String.class.getName())){
						f.set(t,rs.getString(fs[i].getName()));
					}
					else if(f.getType().getName().equals(int.class.getName())){
						f.set(t,rs.getInt(fs[i].getName()));
					}
					else if(f.getType().getName().equals(long.class.getName())){
						f.set(t,rs.getLong(fs[i].getName()));
					}
					else if(f.getType().getName().equals(Date.class.getName())){
						f.set(t,rs.getDate(fs[i].getName()));
					}
					else if(f.getType().getName().equals(float.class.getName())){
						f.set(t,rs.getFloat(fs[i].getName()));
					}
				}
				list.add(t);
			}
		}
		return list;
	}
}
