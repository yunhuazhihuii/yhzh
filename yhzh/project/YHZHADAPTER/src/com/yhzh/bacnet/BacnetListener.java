package com.yhzh.bacnet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.serotonin.bacnet4j.RemoteDevice;
import com.serotonin.bacnet4j.RemoteObject;
import com.serotonin.bacnet4j.event.DefaultDeviceEventListener;
import com.serotonin.bacnet4j.obj.BACnetObject;
import com.serotonin.bacnet4j.service.confirmed.ReinitializeDeviceRequest.ReinitializedStateOfDevice;
import com.serotonin.bacnet4j.type.Encodable;
import com.serotonin.bacnet4j.type.constructed.Choice;
import com.serotonin.bacnet4j.type.constructed.DateTime;
import com.serotonin.bacnet4j.type.constructed.PropertyValue;
import com.serotonin.bacnet4j.type.constructed.SequenceOf;
import com.serotonin.bacnet4j.type.constructed.TimeStamp;
import com.serotonin.bacnet4j.type.enumerated.EventState;
import com.serotonin.bacnet4j.type.enumerated.EventType;
import com.serotonin.bacnet4j.type.enumerated.MessagePriority;
import com.serotonin.bacnet4j.type.enumerated.NotifyType;
import com.serotonin.bacnet4j.type.notificationParameters.NotificationParameters;
import com.serotonin.bacnet4j.type.primitive.Boolean;
import com.serotonin.bacnet4j.type.primitive.CharacterString;
import com.serotonin.bacnet4j.type.primitive.ObjectIdentifier;
import com.serotonin.bacnet4j.type.primitive.UnsignedInteger;

/**bacnet 控制器的监听器
 * @author samsun 2018-1-6
 *
 */
public class BacnetListener extends DefaultDeviceEventListener {
	

	public static Log LOG = LogFactory.getLog(BacnetListener.class); 
	
	private BacnetManager bacnetManager;
	
	public void listenerException(Throwable e) {
        System.out.println("DiscoveryTest listenerException");
    }

    /* (non-Javadoc)监听到有远程ba设备回应连接，暂不采用该种方式初始化远程设备，因为这样就没办法知道yhzh_controller表中哪个设备初始化失败了
     * @see com.serotonin.bacnet4j.event.DefaultDeviceEventListener#iAmReceived(com.serotonin.bacnet4j.RemoteDevice)
     */
    public void iAmReceived(RemoteDevice d) {
//        System.out.println("DiscoveryTest iAmReceived");
//        remoteDevices.add(d);
//        synchronized (ReadAllAvailableProperties.this) {
//            ReadAllAvailableProperties.this.notifyAll();
//        }
    }

    public boolean allowPropertyWrite(BACnetObject obj, PropertyValue pv) {
        System.out.println("DiscoveryTest allowPropertyWrite");
        return true;
    }

    public void propertyWritten(BACnetObject obj, PropertyValue pv) {
        System.out.println("DiscoveryTest propertyWritten");
    }

    public void iHaveReceived(RemoteDevice d, RemoteObject o) {
        System.out.println("DiscoveryTest iHaveReceived");
    }

    /* 监听点状态变化
     * 
     */
    public void covNotificationReceived(UnsignedInteger subscriberProcessIdentifier,
            RemoteDevice initiatingDevice, ObjectIdentifier monitoredObjectIdentifier,
            UnsignedInteger timeRemaining, SequenceOf<PropertyValue> listOfValues) {
    	try {
//			LOG.debug(" 收到监控变化，deviceid:" + initiatingDevice.getInstanceNumber() + 
//					"点为：" + monitoredObjectIdentifier.getObjectType() + " " + monitoredObjectIdentifier.getInstanceNumber() + 
//					";值为："+ listOfValues.getValues().get(0).getValue());
    		bacnetManager.listenPointChg(initiatingDevice.getAddress().toIpString(), initiatingDevice.getInstanceNumber(), 
    				monitoredObjectIdentifier.getObjectType(), monitoredObjectIdentifier.getInstanceNumber(),
    				listOfValues.getValues().get(0).getValue());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void eventNotificationReceived(UnsignedInteger processIdentifier, RemoteDevice initiatingDevice,
            ObjectIdentifier eventObjectIdentifier, TimeStamp timeStamp, UnsignedInteger notificationClass,
            UnsignedInteger priority, EventType eventType, CharacterString messageText, NotifyType notifyType,
            Boolean ackRequired, EventState fromState, EventState toState, NotificationParameters eventValues) {
        System.out.println("DiscoveryTest eventNotificationReceived");
    }

    public void textMessageReceived(RemoteDevice textMessageSourceDevice, Choice messageClass,
            MessagePriority messagePriority, CharacterString message) {
        System.out.println("DiscoveryTest textMessageReceived");
    }

    public void privateTransferReceived(UnsignedInteger vendorId, UnsignedInteger serviceNumber,
            Encodable serviceParameters) {
        System.out.println("DiscoveryTest privateTransferReceived");
    }

    public void reinitializeDevice(ReinitializedStateOfDevice reinitializedStateOfDevice) {
        System.out.println("DiscoveryTest reinitializeDevice");
    }

    @Override
    public void synchronizeTime(DateTime dateTime, boolean utc) {
        System.out.println("DiscoveryTest synchronizeTime");
    }

	public void setBacnetManager(BacnetManager bacnetManager) {
		this.bacnetManager = bacnetManager;
	}
    
    
}
