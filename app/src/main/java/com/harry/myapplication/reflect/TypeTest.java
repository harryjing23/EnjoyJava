package com.harry.myapplication.reflect;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created on 2021/7/1.
 *
 * @author harry
 */
public class TypeTest implements Parcelable {
    // Type接口是所有类型的父接口，与泛型相关
    // 其中有一个实现类Class、四个子接口GenericArrayType ParameterizedType TypeVariable WildcardType
    // 1. ParameterizedType是泛型类 eg. List<T>
    // 2. TypeVariable是泛型参数 eg. T t
    // 3. GenericArrayType是ParameterizedType、TypeVariable的数组 eg. T[], List<T>[]
    // 4. WildcardType是泛型（通配符）表达式， eg. <? extends Number>, <? super Integer>
    // 5. Class是Class对象，每个类都有一个Class对象
    // 如类型确定，可将Type对象直接强转成子接口


    private String a;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.a);
    }

    public void readFromParcel(Parcel source) {
        this.a = source.readString();
    }

    public TypeTest() {
    }

    protected TypeTest(Parcel in) {
        this.a = in.readString();
    }

    public static final Parcelable.Creator<TypeTest> CREATOR = new Parcelable.Creator<TypeTest>() {
        @Override
        public TypeTest createFromParcel(Parcel source) {
            return new TypeTest(source);
        }

        @Override
        public TypeTest[] newArray(int size) {
            return new TypeTest[size];
        }
    };
}
