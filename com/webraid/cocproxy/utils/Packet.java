package com.webraid.cocproxy.utils;

import java.util.Arrays;

public class Packet {

	public enum PacketType {
		MESSAGE_GENERAL("57123"), UNKNOWN(""), CLAN_MESSAGE("55-21"), SYNC(
				"5522"), AUTHENTIFICATION("39117"), LOADING_GAME("4116"), GOBLIN_ATTACK(
				"5554"), RETURN_VILLAGE("5521"), KEEP_ALIVE("39124");

		private String bytes;

		PacketType(String b) {
			this.bytes = b;
		}

		public static PacketType getFromByteArray(byte[] bytes, PacketSide ps) {
			if (ps == PacketSide.FROM_CLIENT) {
				for (PacketType p : PacketType.values()) {
					String valueTypeByte = (bytes[0] + "" + bytes[1]);
					if (valueTypeByte.equalsIgnoreCase(p.getByteValue())) {
						return p;
					}
				}
			} else {
			}
			return PacketType.UNKNOWN;
		}

		public static String getPacketByteValue(byte[] bytes) {
			return (bytes[0] + "" + bytes[1]);
		}

		private String getByteValue() {
			return bytes;
		}
	}

	public enum PacketSide {
		FROM_CLIENT, FROM_SERVER
	}

	private PacketType type;
	private byte[] s;
	private PacketSide ps;
	private int length;

	public Packet(byte[] msg, PacketSide s) {
		this.s = msg;
		this.length = msg[4];
		this.ps = s;
		this.type = PacketType.getFromByteArray(msg, ps);
	}

	public PacketType getType() {
		return type;
	}

	public String decrypt() {
		// ((A_1[2] << 0x10) | (A_1[3] << 8)) | A_1[4]

		/*
		 * if (this.type == PacketType.MESSAGE_GENERAL) { byte[] A_1 = new
		 * String(this.s).substring(7).getBytes(); int num = ((A_1[2] << 0x10) |
		 * (A_1[3] << 8)) | A_1[4]; num = num < 0 ? num * -1 : num;
		 * System.out.println(Integer.toString(num).substring(1, 2)); byte[]
		 * bytes = Arrays.copyOf(A_1, num); Crypt.reverse(new
		 * String(Crypt.decryptKey).getBytes(), bytes); }
		 */

		return null;
	}

	public void test() {
	}

	public String toString() {
		if (this.ps == PacketSide.FROM_CLIENT) {
			StringBuilder sb = new StringBuilder();
			sb.append("Packet side : " + this.ps.name() + "\n");
			sb.append("Type (" + PacketType.getPacketByteValue(this.s) + ") : "
					+ this.type.name() + "\n");
			sb.append("Length of whole data : " + this.s.length + "\n");
			sb.append("Length : " + this.s[4] + "\n");
			sb.append("Data : " + Arrays.toString(this.s) + "\n");
			return sb.toString();
		}
		return null;
	}

	public int getLength() {
		return length;
	}
	
	public PacketSide getPacketSide(){
		return this.ps;
	}

}
