/**  
 * Project Name:LocationServiceProject  
 * File Name:GDLocation.java  
 * Package Name:com.amap.apis.locationservice  
 * Date:2015年5月7日上午11:22:22  
 *  
 */

package com.amap.apis.locationservice;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.LocationProviderProxy;

import android.location.LocationManager;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;

public class GDLocation implements Parcelable {

	public static final int TypeGpsLocation = 61;
	public static final int TypeNetWorkLocation = 161;

	//定位错误标识码
    public static final int TypeServerError = 167;

	private String mAddress;

	private String mDistrict;

	private double mLatitude;

	private double mLongtitude;

	private double mAltitude;

	private float mRadius;

	private float mSpeed;

	private String mStreet;

	private String mCity;

	private String mCityCode;

	private String mProvince;

	private String mTime;

	private String mLocationType;

	private String mFloor;

	public GDLocation() {

	}

	void setAMapLocaion(AMapLocation amapLocation) {
		if(amapLocation.getAMapException()!=null
				&&amapLocation.getAMapException().getErrorCode()==0)
		{
		mAddress = amapLocation.getAddress();
		mDistrict = amapLocation.getDistrict();
		mLatitude = amapLocation.getLatitude();
		mLongtitude = amapLocation.getLongitude();
		mAltitude = amapLocation.getAltitude();
		mRadius = amapLocation.getAccuracy();
		mSpeed = amapLocation.getSpeed();
		mStreet = amapLocation.getStreet();
		mCity = amapLocation.getCity();
		mCityCode = amapLocation.getCityCode();
		mProvince = amapLocation.getProvince();
		long time = amapLocation.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(time);
		mTime = df.format(date);
		mLocationType = amapLocation.getProvider();
		mFloor = amapLocation.getFloor();
		}
		else{
			mAddress = amapLocation.getAddress();
			mDistrict = amapLocation.getDistrict();
			mLatitude = amapLocation.getLatitude();
			mLongtitude = amapLocation.getLongitude();
			mAltitude = amapLocation.getAltitude();
			mRadius = amapLocation.getAccuracy();
			mSpeed = amapLocation.getSpeed();
			mStreet = amapLocation.getStreet();
			mCity = amapLocation.getCity();
			mCityCode = amapLocation.getCityCode();
			mProvince = amapLocation.getProvince();
			long time = amapLocation.getTime();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date(time);
			mTime = df.format(date);
			 mLocationType="TypeServerError";;
			mFloor = amapLocation.getFloor();
			Log.i("location", "error code "+amapLocation.getAMapException().getErrorCode());
			
		}

	}

 
	/**
	 *获取详细地址信息
	 * */
	public String getAddrStr() {
		return mAddress;
	}

	 
	/**
	 *获取区/县信息
	 * */
	public String getDistrict() {
		return mDistrict;
	}

 
	/**
	 *获取纬度坐标
	 * */
	public double getLatitude() {
		return mLatitude;
	}

 
	/**
	 *获取经度坐标
	 * */
	public double getLongitude() {
		return mLongtitude;
	}

	 
	/**
	 *获取定位精度
	 * */
	public float getRadius() {
		return mRadius;
	}

	public void setLatitude(double latitude) {

		mLatitude = latitude;
	}

	public void setLongitude(double longitude) {
		mLongtitude = longitude;

	}

	 
	/**
	 *获取速度，仅gps定位结果时有速度信息
	 * */
	public float getSpeed() {
		return mSpeed;
	}

	public void setSpeed(float speed) {
		mSpeed = speed;

	}

	 
	/**
	 *获取城市
	 * */
	public String getCity() {
		return mCity;

	}

 
	/**
	 *获取街道信息
	 * */
	public String getStreet() {
		return mStreet;
	}

	public String getCityCode() {
		return mCityCode;
	}

	public void setAddrStr(String address) {

		mAddress = address;
	}

	/**
	 * 获取省份
	 * */
	public String getProvince() {
		return mProvince;
	}

	public void setRadius(float radius) {

		mRadius = radius;
	}

	public void setAltitude(double altitude) {
		mAltitude = altitude;
	}

	/**
	 * 获取高度信息，目前没有实现
	 * */
	public double getAltitude() {
		return mAltitude;
	}

	public int describeContents() {
		return 0;
	}

	public static final Parcelable.Creator<GDLocation> CREATOR = new Creator<GDLocation>() {

		@Override
		public GDLocation createFromParcel(Parcel in) {
			return new GDLocation(in);
		}

		@Override
		public GDLocation[] newArray(int size) {

			// TODO Auto-generated method stub
			return new GDLocation[size];
		}
	};

	private GDLocation(Parcel in) {

		mAddress = in.readString();
		mDistrict = in.readString();
		mLatitude = in.readDouble();
		mLongtitude = in.readDouble();
		mAltitude = in.readDouble();
		mRadius = in.readFloat();
		mSpeed = in.readFloat();
		mStreet = in.readString();
		mCity = in.readString();
		mCityCode = in.readString();
		mProvince = in.readString();
		mTime = in.readString();
		mLocationType = in.readString();
		mFloor = in.readString();

	}

	public void writeToParcel(Parcel dest, int arg1) {
		dest.writeString(mAddress);
		dest.writeString(mDistrict);
		dest.writeDouble(mLatitude);
		dest.writeDouble(mLongtitude);
		dest.writeDouble(mAltitude);
		dest.writeFloat(mRadius);
		dest.writeFloat(mSpeed);
		dest.writeString(mStreet);
		dest.writeString(mCity);
		dest.writeString(mCityCode);
		dest.writeString(mProvince);
		dest.writeString(mTime);
		dest.writeString(mLocationType);
		dest.writeString(mFloor);

	}

	/**
	 * 是否有地址信息
	 * */
	public boolean hasAddr() {
		return !TextUtils.isEmpty(mAddress);
	}

	/**
	 * server返回的当前定位时间
	 * */
	public String getTime() {
		return mTime;
	}

	/**
	 * 获取定位类型: 参考 定位结果描述 相关的字段
	 * */
	public int getLocType() {
		if (LocationManager.GPS_PROVIDER.equals(mLocationType)) {
			return TypeGpsLocation;
		} 
		
		else if (LocationProviderProxy.AMapNetwork.equals(mLocationType)
				||LocationManager.NETWORK_PROVIDER.equals(mLocationType)
				) {
			return TypeNetWorkLocation;
		}
		else if("TypeServerError".equals(mLocationType)){
			return TypeServerError;
		}
		return 0;
	}

	public void setTime(String time) {

		mTime = time;
	}

	/**
	 * 获取楼层信息,仅室内定位时有效
	 * */
	public String getFloor() {
		return mFloor;
	}

}
