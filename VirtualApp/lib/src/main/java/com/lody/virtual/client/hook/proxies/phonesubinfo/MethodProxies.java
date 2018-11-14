package com.lody.virtual.client.hook.proxies.phonesubinfo;

import android.text.TextUtils;

import com.lody.virtual.client.core.VirtualCore;
import com.lody.virtual.client.hook.base.ReplaceLastPkgMethodProxy;
import com.lody.virtual.helper.utils.marks.FakeDeviceMark;

import java.lang.reflect.Method;

/**
 * @author Lody
 */
@SuppressWarnings("ALL")
class MethodProxies {

    @FakeDeviceMark("fake device id")
    static class GetDeviceId extends ReplaceLastPkgMethodProxy {
        public GetDeviceId() {
            super("getDeviceId");
        }

        @Override
        public Object call(Object who, Method method, Object... args) throws Throwable {
            String deviceId = getDeviceInfo().getDeviceId();
            if (!TextUtils.isEmpty(deviceId)) {
                return deviceId;
            }
            if(VirtualCore.get().hasPermission(android.Manifest.permission.READ_PHONE_STATE)) {
                return super.call(who, method, args);
            }
            return "0000000000000000";
        }
    }

    //
    @FakeDeviceMark("fake device id")
    static class GetDeviceIdForPhone extends GetDeviceId {
        @Override
        public String getMethodName() {
            return "getDeviceIdForPhone";
        }
    }

    @FakeDeviceMark("fake device id.")
    static class GetDeviceIdForSubscriber extends GetDeviceId {

        @Override
        public String getMethodName() {
            return "getDeviceIdForSubscriber";
        }

    }

    @FakeDeviceMark("fake device id.")
    static class GetImeiForSubscriber extends GetDeviceId {

        @Override
        public String getMethodName() {
            return "getImeiForSubscriber";
        }

    }

    @FakeDeviceMark("fake iccid")
    static class GetIccSerialNumber extends ReplaceLastPkgMethodProxy {
        public GetIccSerialNumber() {
            super("getIccSerialNumber");
        }

        @Override
        public Object call(Object who, Method method, Object... args) throws Throwable {
            String iccId = getDeviceInfo().getIccId();
            if (!TextUtils.isEmpty(iccId)) {
                return iccId;
            }
            if(VirtualCore.get().hasPermission(android.Manifest.permission.READ_PHONE_STATE)) {
                return super.call(who, method, args);
            }
            return null;
        }
    }

    static class GetIccSerialNumberForSubscriber extends GetIccSerialNumber {
        @Override
        public String getMethodName() {
            return "getIccSerialNumberForSubscriber";
        }
    }
}
