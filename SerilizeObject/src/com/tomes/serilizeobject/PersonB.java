package com.tomes.serilizeobject;

import java.util.HashMap;
import java.util.Map;

import android.os.Parcel;
import android.os.Parcelable;

/**把要传递的参数封装到一个对象中，去实现Parcelable接口
 * @author Tomes
 * 
 */
public class PersonB implements Parcelable {

	int age;
	String name;
	Map student = new HashMap<>();
	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeInt(age);
		dest.writeString(name);
		dest.writeMap(student);
	}
	//CREATOR这个命名是固定写法，不能写成其他名字，否则会出现android.os.BadParcelableException: Parcelable protocol requires a Parcelable.Creator object called  CREATOR on class com.tomes.serilizeobject.PersonB
	public static final Creator<PersonB> CREATOR=new Creator<PersonB>() {
		
		@Override
		public PersonB[] newArray(int size) {
			return new PersonB[size];
		}
		
		@Override
		public PersonB createFromParcel(Parcel source) {
			PersonB personB=new PersonB();
			personB.age=source.readInt();
			personB.name=source.readString();
			personB.student=source.readHashMap(HashMap.class.getClassLoader());
			return personB;
		}
	};
}
