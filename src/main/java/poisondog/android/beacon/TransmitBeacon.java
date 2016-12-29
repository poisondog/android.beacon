/*
 * Copyright (C) 2016 Adam Huang <poisondog@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package poisondog.android.beacon;

import android.content.Context;
import java.util.Arrays;
import java.util.List;
import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.BeaconTransmitter;
import poisondog.core.Mission;

/**
 * @author Adam Huang
 * @since 2016-12-22
 */
public class TransmitBeacon implements Mission<TransmitBeacon.Parameter> {

	@Override
	public BeaconTransmitter execute(TransmitBeacon.Parameter parameter) {
		Beacon beacon = new Beacon.Builder()
			.setId1(parameter.mUUID)
			.setId2(parameter.mMajor)
			.setId3(parameter.mMinor)
			.setManufacturer(parameter.mManufacturer)
			.setTxPower(parameter.mTxPower)
			.setDataFields(parameter.mDataFields)
			.setBluetoothAddress(parameter.mBluetoothAddress)
			.setBluetoothName(parameter.mBluetoothName)
			.build();
		System.out.println("beacon: " + beacon);
		System.out.println("beacon address: " + beacon.getBluetoothAddress());
		System.out.println("beacon type code: " + beacon.getBeaconTypeCode());
		BeaconParser beaconParser = new BeaconParser().setBeaconLayout(parameter.mBeaconLayout);
		BeaconTransmitter beaconTransmitter = new BeaconTransmitter(parameter.mContext, beaconParser);
		beaconTransmitter.setBeacon(beacon);
//		beaconTransmitter.startAdvertising(beacon);
		return beaconTransmitter;
	}

	public static class Parameter {
		private Context mContext;
		private String mBeaconLayout;
		private String mUUID;
		private String mMajor;
		private String mMinor;
		private String mBluetoothAddress;
		private String mBluetoothName;
		private int mManufacturer;
		private int mTxPower;
		private List<Long> mDataFields;

		/**
		 * Constructor
		 */
		public Parameter(Context context, String uuid, String major, String minor) {
			mContext = context;
			mUUID = uuid;
			mMajor = major;
			mMinor = minor;
			mManufacturer = 0x0118;
			mTxPower = -59;
			//AltBeacon
//			mBeaconLayout = "m:2-3=beac,i:4-19,i:20-21,i:22-23,p:24-24,d:25-25";
			//iBeacon
			mBeaconLayout = "m:2-3=0215,i:4-19,i:20-21,i:22-23,p:24-24";
			mDataFields = Arrays.asList(new Long[] {0l});
		}

		public void setBluetoothAddress(String address) {
			mBluetoothAddress = address;
		}

		public void setBluetoothName(String name) {
			mBluetoothName = name;
		}
	}

}
