package net.osmand.plus.backup;

import androidx.annotation.NonNull;

import net.osmand.plus.settings.backend.backup.items.SettingsItem;
import net.osmand.util.Algorithms;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

public class RemoteFile {

	private int userid;
	private long id;
	private int deviceid;
	private int filesize;
	private String type;
	private String name;
	private Date updatetime;
	private long updatetimems;
	private Date clienttime;
	private long clienttimems;
	private int zipSize;

	protected SettingsItem item;

	public RemoteFile(@NonNull JSONObject json) throws JSONException, ParseException {
		if (json.has("userid")) {
			userid = json.getInt("userid");
		}
		if (json.has("id")) {
			id = json.getLong("id");
		}
		if (json.has("deviceid")) {
			deviceid = json.getInt("deviceid");
		}
		if (json.has("filesize")) {
			filesize = json.getInt("filesize");
		}
		if (json.has("type")) {
			type = json.getString("type");
		}
		if (json.has("name")) {
			name = json.getString("name");
		}
		if (json.has("updatetimems")) {
			updatetimems = json.getLong("updatetimems");
			updatetime = new Date(updatetimems);
		}
		if (json.has("clienttimems")) {
			clienttimems = json.getLong("clienttimems");
			clienttime = new Date(clienttimems);
		}
		if (json.has("zipSize")) {
			zipSize = json.getInt("zipSize");
		}
	}

	public int getUserid() {
		return userid;
	}

	public long getId() {
		return id;
	}

	public int getDeviceid() {
		return deviceid;
	}

	public int getFilesize() {
		return filesize;
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public String getTypeNamePath() {
		if (!Algorithms.isEmpty(name)) {
			return type + (name.charAt(0) == '/' ? name : "/" + name);
		} else {
			return type;
		}
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public long getUpdatetimems() {
		return updatetimems;
	}

	public Date getClienttime() {
		return clienttime;
	}

	public long getClienttimems() {
		return clienttimems;
	}

	public int getZipSize() {
		return zipSize;
	}
}
