package com.appstek.dc.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;
import com.appstek.dc.util.MappingConstants;

public class ApplyingFieldRules {
	public static String field_76_Type;

	public static int field_37_value;// Its value will set from 37 field
										// validation
	public static int field_38_value;// Its value will set from 38 field
										// validation
	public static int field_94_value;// Its value will set from 94 field
										// validation
	public static int field_95_value;// Its value will set from 95 field
										// validation
	static Pattern pattern = Pattern.compile("[^a-zA-Z0-9]");
	private static Logger log4j = Logger.getLogger("com.appstek.dc.util.ApplyingFieldRules");

	/*
	 * public static String fieldRules(int fieldNumber, String fieldValue, int
	 * length, Object[] singleRowData, List<Object[]> list, String
	 * fieldsNumbers,Map<String,String> realMap) {
	 */
	public static Map<String, String> fieldRules(int fieldNumber, String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, String fieldsNumbers, Map<String, String> realMap, Map<String, String> dummyValueHoldingMap) {
		// Map<String,String> realMap = RecordTypeTableConstant.realMap;

		// fieldValue = applyRulesForLengthofField(fieldValue,length);
		switch (fieldNumber) {
		case 1:
			ApplyingFieldRules.for_Field_1(fieldValue, length, singleRowData, list, realMap);
			break;
		case 2:
			ApplyingFieldRules.for_Field_2(fieldValue, length, singleRowData, list, realMap, fieldsNumbers);
			break;
		case 3:
			ApplyingFieldRules.forField_3(fieldValue, length, singleRowData, list, realMap, fieldsNumbers);
			break;
		case 4:
			ApplyingFieldRules.forField_4(fieldValue, length, singleRowData, list, realMap);
			break;
		case 5:
			ApplyingFieldRules.forField_5(fieldValue, length, singleRowData, list, realMap, fieldsNumbers);
			break;
		case 6:
			ApplyingFieldRules.forField_6(fieldValue, length, singleRowData, list, realMap);
			break;
		case 7:
			ApplyingFieldRules.forField_7(fieldValue, length, singleRowData, list, realMap);
			break;
		case 8:
			ApplyingFieldRules.forField_8(fieldValue, length, singleRowData, list, realMap);
			break;
		case 9:
			ApplyingFieldRules.forField_9(fieldValue, length, singleRowData, list, realMap);
			break;
		case 10:
			ApplyingFieldRules.forField_10(fieldValue, length, singleRowData, list, realMap);
			break;
		case 11:
			ApplyingFieldRules.forField_11(fieldValue, length, singleRowData, list, realMap);
			break;
		case 12:
			ApplyingFieldRules.forField_12(fieldValue, length, singleRowData, list, realMap);
			break;
		case 13:
			ApplyingFieldRules.forField_13(fieldValue, length, singleRowData, list, realMap);
			break;
		case 14:
			ApplyingFieldRules.forField_14(fieldValue, length, singleRowData, list, realMap);
			break;
		case 15:
			ApplyingFieldRules.forField_15(fieldValue, length, singleRowData, list, realMap);
			break;
		case 16:
			ApplyingFieldRules.forField_16(fieldValue, length, singleRowData, list, realMap);
			break;
		case 17:
			ApplyingFieldRules.forField_17(fieldValue, length, singleRowData, list, realMap);
			break;
		case 18:
			ApplyingFieldRules.forField_18(fieldValue, length, singleRowData, list, realMap,dummyValueHoldingMap);
			break;
		case 19:
			ApplyingFieldRules.forField_19(fieldValue, length, singleRowData, list, realMap, fieldsNumbers,dummyValueHoldingMap);
			break;
		case 20:
			ApplyingFieldRules.forField_20(fieldValue, length, singleRowData, list, realMap);
			break;
		case 21:
			ApplyingFieldRules.forField_21(fieldValue, length, singleRowData, list, realMap);
			break;
		case 22:
			ApplyingFieldRules.forField_22(fieldValue, length, singleRowData, list, realMap);
			break;
		case 23:
			ApplyingFieldRules.forField_23(fieldValue, length, singleRowData, list, realMap);
			break;
		case 24:
			ApplyingFieldRules.forField_24(fieldValue, length, singleRowData, list, realMap);
			break;
		case 25:
			ApplyingFieldRules.forField_25(fieldValue, length, singleRowData, list, realMap);
			break;
		case 26:
			ApplyingFieldRules.forField_26(fieldValue, length, singleRowData, list, realMap);
			break;
		case 27:
			ApplyingFieldRules.forField_27(fieldValue, length, singleRowData, list, realMap);
			break;
		case 28:
			ApplyingFieldRules.forField_28(fieldValue, length, singleRowData, list, realMap);
			break;
		case 29:
			ApplyingFieldRules.forField_29(fieldValue, length, singleRowData, list, realMap);
			break;
		case 30:
			ApplyingFieldRules.forField_30(fieldValue, length, singleRowData, list, realMap);
			break;
		case 31:
			ApplyingFieldRules.forField_31(fieldValue, length, singleRowData, list, realMap);
			break;
		case 32:
			ApplyingFieldRules.forField_32(fieldValue, length, singleRowData, list, realMap);
			break;
		case 33:
			ApplyingFieldRules.forField_33(fieldValue, length, singleRowData, list, realMap);
			break;
		case 34:
			ApplyingFieldRules.forField_34(fieldValue, length, singleRowData, list, realMap);
			break;
		case 35:
			ApplyingFieldRules.forField_35(fieldValue, length, singleRowData, list, realMap);
			break;
		case 36:
			ApplyingFieldRules.forField_36(fieldValue, length, singleRowData, list, realMap);
			break;
		case 37:
			ApplyingFieldRules.forField_37(fieldValue, length, singleRowData, list, realMap);
			break;
		case 38:
			ApplyingFieldRules.forField_38(fieldValue, length, singleRowData, list, realMap);
			break;
		case 39:
			ApplyingFieldRules.forField_39(fieldValue, length, singleRowData, list, realMap, fieldsNumbers);
			break;
		case 40:
			ApplyingFieldRules.forField_40(fieldValue, length, singleRowData, list, realMap);
			break;
		case 41:
			ApplyingFieldRules.forField_41(fieldValue, length, singleRowData, list, realMap);
			break;
		case 42:
			ApplyingFieldRules.forField_42(fieldValue, length, singleRowData, list, realMap);
			break;
		case 43:
			ApplyingFieldRules.forField_43(fieldValue, length, singleRowData, list, realMap);
			break;
		case 44:
			ApplyingFieldRules.forField_44(fieldValue, length, singleRowData, list, realMap);
			break;
		case 45:
			ApplyingFieldRules.forField_45(fieldValue, length, singleRowData, list, realMap);
			break;
		case 46:
			ApplyingFieldRules.forField_46(fieldValue, length, singleRowData, list, realMap);
			break;
		case 47:
			ApplyingFieldRules.forField_47(fieldValue, length, singleRowData, list, realMap, fieldsNumbers);
			break;
		case 48:
			ApplyingFieldRules.forField_48(fieldValue, length, singleRowData, list, realMap, fieldsNumbers);
			break;
		case 49:
			ApplyingFieldRules.forField_49(fieldValue, length, singleRowData, list, realMap, fieldsNumbers);
			break;
		case 50:
			ApplyingFieldRules.forField_50(fieldValue, length, singleRowData, list, realMap);
			break;
		case 51:
			ApplyingFieldRules.forField_51(fieldValue, length, singleRowData, list, realMap);
			break;
		case 52:
			ApplyingFieldRules.forField_52(fieldValue, length, singleRowData, list, realMap, fieldsNumbers);
			break;
		case 53:
			ApplyingFieldRules.forField_53(fieldValue, length, singleRowData, list, realMap, fieldsNumbers);
			break;
		case 54:
			ApplyingFieldRules.forField_54(fieldValue, length, singleRowData, list, realMap);
			break;
		case 55:
			ApplyingFieldRules.forField_55(fieldValue, length, singleRowData, list, realMap, fieldsNumbers);
			break;
		case 56:
			ApplyingFieldRules.forField_56(fieldValue, length, singleRowData, list, realMap, fieldsNumbers);
			break;
		case 57:
			ApplyingFieldRules.forField_57(fieldValue, length, singleRowData, list, realMap, fieldsNumbers);
			break;
		case 58:
			ApplyingFieldRules.forField_58(fieldValue, length, singleRowData, list, realMap, fieldsNumbers);
			break;
		case 59:
			ApplyingFieldRules.forField_59(fieldValue, length, singleRowData, list, realMap, fieldsNumbers);
			break;
		case 60:
			ApplyingFieldRules.forField_60(fieldValue, length, singleRowData, list, realMap, fieldsNumbers);
			break;
		case 61:
			ApplyingFieldRules.forField_61(fieldValue, length, singleRowData, list, realMap, fieldsNumbers);
			break;
		case 62:
			ApplyingFieldRules.forField_62(fieldValue, length, singleRowData, list, realMap, fieldsNumbers);
			break;
		case 63:
			ApplyingFieldRules.forField_63(fieldValue, length, singleRowData, list, realMap, fieldsNumbers);
			break;
		case 64:
			ApplyingFieldRules.forField_64(fieldValue, length, singleRowData, list, realMap, fieldsNumbers);
			break;
		case 65:
			ApplyingFieldRules.forField_65(fieldValue, length, singleRowData, list, realMap, fieldsNumbers);
			break;
		case 66:
			ApplyingFieldRules.forField_66(fieldValue, length, singleRowData, list, realMap, fieldsNumbers);
			break;
		case 67:
			ApplyingFieldRules.forField_67(fieldValue, length, singleRowData, list, realMap, fieldsNumbers);
			break;
		case 68:
			ApplyingFieldRules.forField_68(fieldValue, length, singleRowData, list, realMap);
			break;
		case 69:
			ApplyingFieldRules.forField_69(fieldValue, length, singleRowData, list, realMap);
			break;
		case 70:
			ApplyingFieldRules.forField_70(fieldValue, length, singleRowData, list, realMap);
			break;
		case 71:
			ApplyingFieldRules.forField_71(fieldValue, length, singleRowData, list, realMap);
			break;
		case 72:
			ApplyingFieldRules.forField_72(fieldValue, length, singleRowData, list, realMap);
			break;
		case 73:
			ApplyingFieldRules.forField_73(fieldValue, length, singleRowData, list, realMap);
			break;
		case 74:
			ApplyingFieldRules.forField_74(fieldValue, length, singleRowData, list, realMap);
			break;
		case 75:
			ApplyingFieldRules.forField_75(fieldValue, length, singleRowData, list, realMap);
			break;
		case 76:
			ApplyingFieldRules.forField_76(fieldValue, length, singleRowData, list, realMap, fieldsNumbers);
			break;
		case 77:
			ApplyingFieldRules.forField_77(fieldValue, length, singleRowData, list, realMap);
			break;
		case 78:
			ApplyingFieldRules.forField_78(fieldValue, length, singleRowData, list, realMap);
			break;
		case 79:
			ApplyingFieldRules.forField_79(fieldValue, length, singleRowData, list, realMap);
			break;
		case 80:
			ApplyingFieldRules.forField_80(fieldValue, length, singleRowData, list, realMap);
			break;
		case 81:
			ApplyingFieldRules.forField_81(fieldValue, length, singleRowData, list, realMap);
			break;
		case 82:
			ApplyingFieldRules.forField_82(fieldValue, length, singleRowData, list, realMap);
			break;
		case 83:
			ApplyingFieldRules.forField_83(fieldValue, length, singleRowData, list, realMap);
			break;
		case 84:
			ApplyingFieldRules.forField_84(fieldValue, length, singleRowData, list, realMap);
			break;
		case 85:
			ApplyingFieldRules.forField_85(fieldValue, length, singleRowData, list, realMap);
			break;
		case 86:
			ApplyingFieldRules.forField_86(fieldValue, length, singleRowData, list, realMap);
			break;
		case 87:
			ApplyingFieldRules.forField_87(fieldValue, length, singleRowData, list, realMap);
			break;
		case 88:
			ApplyingFieldRules.forField_88(fieldValue, length, singleRowData, list, realMap);
			break;
		case 89:
			ApplyingFieldRules.forField_89(fieldValue, length, singleRowData, list, realMap);
			break;
		case 90:
			ApplyingFieldRules.forField_90(fieldValue, length, singleRowData, list, realMap);
			break;
		case 91:
			ApplyingFieldRules.forField_91(fieldValue, length, singleRowData, list, realMap);
			break;
		case 92:
			ApplyingFieldRules.forField_92(fieldValue, length, singleRowData, list, realMap);
			break;
		case 93:
			ApplyingFieldRules.forField_93(fieldValue, length, singleRowData, list, realMap);
			break;
		case 94:
			ApplyingFieldRules.forField_94(fieldValue, length, singleRowData, list, realMap);
			break;
		case 95:
			ApplyingFieldRules.forField_95(fieldValue, length, singleRowData, list, realMap);
			break;
		case 96:
			ApplyingFieldRules.forField_96(fieldValue, length, singleRowData, list, realMap, fieldsNumbers);
			break;
		case 97:
			ApplyingFieldRules.forField_97(fieldValue, length, singleRowData, list, realMap);
			break;
		case 98:
			ApplyingFieldRules.forField_98(fieldValue, length, singleRowData, list, realMap);
			break;
		case 99:
			ApplyingFieldRules.forField_99(fieldValue, length, singleRowData, list, realMap);
			break;
		case 100:
			ApplyingFieldRules.forField_100(fieldValue, length, singleRowData, list, realMap);
			break;
		case 101:
			ApplyingFieldRules.forField_101(fieldValue, length, singleRowData, list, realMap);
			break;
		case 102:
			ApplyingFieldRules.forField_102(fieldValue, length, singleRowData, list, realMap);
			break;
		case 103:
			ApplyingFieldRules.forField_103(fieldValue, length, singleRowData, list, realMap);
			break;
		case 104:
			ApplyingFieldRules.forField_104(fieldValue, length, singleRowData, list, realMap);
			break;
		case 105:
			ApplyingFieldRules.forField_105(fieldValue, length, singleRowData, list, realMap);
			break;
		case 106:
			ApplyingFieldRules.forField_106(fieldValue, length, singleRowData, list, realMap);
			break;
		case 107:
			ApplyingFieldRules.forField_107(fieldValue, length, singleRowData, list, realMap);
			break;
		case 108:
			ApplyingFieldRules.forField_108(fieldValue, length, singleRowData, list, realMap);
			break;
		case 109:
			ApplyingFieldRules.forField_109(fieldValue, length, singleRowData, list, realMap);
			break;
		case 110:
			ApplyingFieldRules.forField_110(fieldValue, length, singleRowData, list, realMap);
			break;
		case 111:
			ApplyingFieldRules.forField_111(fieldValue, length, singleRowData, list, realMap);
			break;
		case 112:
			ApplyingFieldRules.forField_112(fieldValue, length, singleRowData, list, realMap);
			break;
		case 113:
			ApplyingFieldRules.forField_113(fieldValue, length, singleRowData, list, realMap);
			break;
		case 114:
			ApplyingFieldRules.forField_114(fieldValue, length, singleRowData, list, realMap);
			break;
		case 115:
			ApplyingFieldRules.forField_115(fieldValue, length, singleRowData, list, realMap);
			break;
		case 116:
			ApplyingFieldRules.forField_116(fieldValue, length, singleRowData, list, realMap);
			break;
		case 117:
			ApplyingFieldRules.forField_117(fieldValue, length, singleRowData, list, realMap);
			break;
		case 118:
			ApplyingFieldRules.forField_118(fieldValue, length, singleRowData, list, realMap);
			break;
		case 119:
			ApplyingFieldRules.forField_119(fieldValue, length, singleRowData, list, realMap);
			break;
		case 120:
			ApplyingFieldRules.forField_120(fieldValue, length, singleRowData, list, realMap);
			break;
		case 121:
			ApplyingFieldRules.forField_121(fieldValue, length, singleRowData, list, realMap);
			break;
		case 122:
			ApplyingFieldRules.forField_122(fieldValue, length, singleRowData, list, realMap);
			break;
		case 123:
			ApplyingFieldRules.forField_123(fieldValue, length, singleRowData, list, realMap);
			break;
		case 124:
			ApplyingFieldRules.forField_124(fieldValue, length, singleRowData, list, realMap, fieldsNumbers);
			break;
		case 125:
			ApplyingFieldRules.forField_125(fieldValue, length, singleRowData, list, realMap);
			break;
		case 126:
			ApplyingFieldRules.forField_126(fieldValue, length, singleRowData, list, realMap);
			break;
		case 127:
			ApplyingFieldRules.forField_127(fieldValue, length, singleRowData, list, realMap);
			break;
		case 128:
			ApplyingFieldRules.forField_128(fieldValue, length, singleRowData, list, realMap);
			break;
		case 129:
			ApplyingFieldRules.forField_129(fieldValue, length, singleRowData, list, realMap);
			break;
		case 130:
			ApplyingFieldRules.forField_130(fieldValue, length, singleRowData, list, realMap);
			break;
		case 131:
			ApplyingFieldRules.forField_131(fieldValue, length, singleRowData, list, realMap);
			break;
		case 132:
			ApplyingFieldRules.forField_132(fieldValue, length, singleRowData, list, realMap);
			break;
		case 133:
			ApplyingFieldRules.forField_133(fieldValue, length, singleRowData, list, realMap);
			break;
		case 134:
			ApplyingFieldRules.forField_134(fieldValue, length, singleRowData, list, realMap);
			break;
		case 135:
			ApplyingFieldRules.forField_135(fieldValue, length, singleRowData, list, realMap);
			break;
		case 136:
			ApplyingFieldRules.forField_136(fieldValue, length, singleRowData, list, realMap);
			break;
		case 137:
			ApplyingFieldRules.forField_137(fieldValue, length, singleRowData, list, realMap);
			break;
		case 138:
			ApplyingFieldRules.forField_138(fieldValue, length, singleRowData, list, realMap);
			break;
		case 139:
			ApplyingFieldRules.forField_139(fieldValue, length, singleRowData, list, realMap);
			break;
		case 140:
			ApplyingFieldRules.forField_140(fieldValue, length, singleRowData, list, realMap);
			break;
		case 141:
			ApplyingFieldRules.forField_141(fieldValue, length, singleRowData, list, realMap);
			break;
		case 142:
			ApplyingFieldRules.forField_142(fieldValue, length, singleRowData, list, realMap);
			break;
		case 143:
			ApplyingFieldRules.forField_143(fieldValue, length, singleRowData, list, realMap);
			break;
		case 144:
			ApplyingFieldRules.forField_144(fieldValue, length, singleRowData, list, realMap);
			break;
		case 145:
			ApplyingFieldRules.forField_145(fieldValue, length, singleRowData, list, realMap);
			break;
		case 146:
			ApplyingFieldRules.forField_146(fieldValue, length, singleRowData, list, realMap);
			break;
		case 147:
			ApplyingFieldRules.forField_147(fieldValue, length, singleRowData, list, realMap);
			break;
		case 148:
			ApplyingFieldRules.forField_148(fieldValue, length, singleRowData, list, realMap);
			break;
		case 149:
			ApplyingFieldRules.forField_149(fieldValue, length, singleRowData, list, realMap);
			break;
		case 150:
			ApplyingFieldRules.forField_150(fieldValue, length, singleRowData, list, realMap);
			break;
		case 151:
			ApplyingFieldRules.forField_151(fieldValue, length, singleRowData, list, realMap);
			break;
		case 152:
			ApplyingFieldRules.forField_152(fieldValue, length, singleRowData, list, realMap);
			break;
		case 153:
			ApplyingFieldRules.forField_153(fieldValue, length, singleRowData, list, realMap);
			break;
		case 154:
			ApplyingFieldRules.forField_154(fieldValue, length, singleRowData, list, realMap);
			break;
		case 155:
			ApplyingFieldRules.forField_155(fieldValue, length, singleRowData, list, realMap);
			break;
		case 156:
			ApplyingFieldRules.forField_156(fieldValue, length, singleRowData, list, realMap);
			break;
		case 157:
			ApplyingFieldRules.forField_157(fieldValue, length, singleRowData, list, realMap);
			break;
		case 158:
			ApplyingFieldRules.forField_158(fieldValue, length, singleRowData, list, realMap);
			break;
		case 159:
			ApplyingFieldRules.forField_159(fieldValue, length, singleRowData, list, realMap);
			break;
		case 160:
			ApplyingFieldRules.forField_160(fieldValue, length, singleRowData, list, realMap);
			break;
		case 161:
			ApplyingFieldRules.forField_161(fieldValue, length, singleRowData, list, realMap);
			break;
		case 162:
			ApplyingFieldRules.forField_162(fieldValue, length, singleRowData, list, realMap);
			break;
		case 163:
			ApplyingFieldRules.forField_163(fieldValue, length, singleRowData, list, realMap);
			break;
		case 164:
			ApplyingFieldRules.forField_164(fieldValue, length, singleRowData, list, realMap);
			break;
		case 165:
			ApplyingFieldRules.forField_165(fieldValue, length, singleRowData, list, realMap);
			break;
		case 166:
			ApplyingFieldRules.forField_166(fieldValue, length, singleRowData, list, realMap);
			break;
		case 167:
			ApplyingFieldRules.forField_167(fieldValue, length, singleRowData, list, realMap);
			break;
		case 168:
			ApplyingFieldRules.forField_168(fieldValue, length, singleRowData, list, realMap);
			break;
		case 169:
			ApplyingFieldRules.forField_169(fieldValue, length, singleRowData, list, realMap);
			break;
		case 170:
			ApplyingFieldRules.forField_170(fieldValue, length, singleRowData, list, realMap);
			break;
		case 171:
			ApplyingFieldRules.forField_171(fieldValue, length, singleRowData, list, realMap);
			break;
		case 172:
			ApplyingFieldRules.forField_172(fieldValue, length, singleRowData, list, realMap);
			break;
		case 173:
			ApplyingFieldRules.forField_173(fieldValue, length, singleRowData, list, realMap);
			break;
		case 174:
			ApplyingFieldRules.forField_174(fieldValue, length, singleRowData, list, realMap);
			break;
		case 175:
			ApplyingFieldRules.forField_175(fieldValue, length, singleRowData, list, realMap);
			break;
		case 176:
			ApplyingFieldRules.forField_176(fieldValue, length, singleRowData, list, realMap);
			break;
		case 177:
			ApplyingFieldRules.forField_177(fieldValue, length, singleRowData, list, realMap);
			break;
		case 178:
			ApplyingFieldRules.forField_178(fieldValue, length, singleRowData, list, realMap);
			break;
		case 179:
			ApplyingFieldRules.forField_179(fieldValue, length, singleRowData, list, realMap);
			break;
		case 180:
			ApplyingFieldRules.forField_180(fieldValue, length, singleRowData, list, realMap);
			break;
		case 181:
			ApplyingFieldRules.forField_181(fieldValue, length, singleRowData, list, realMap);
			break;
		case 182:
			ApplyingFieldRules.forField_182(fieldValue, length, singleRowData, list, realMap);
			break;
		case 183:
			ApplyingFieldRules.forField_183(fieldValue, length, singleRowData, list, realMap);
			break;
		case 184:
			ApplyingFieldRules.forField_184(fieldValue, length, singleRowData, list, realMap);
			break;
		case 185:
			ApplyingFieldRules.forField_185(fieldValue, length, singleRowData, list, realMap);
			break;
		case 186:
			ApplyingFieldRules.forField_186(fieldValue, length, singleRowData, list, realMap);
			break;
		case 187:
			ApplyingFieldRules.forField_187(fieldValue, length, singleRowData, list, realMap);
			break;
		case 188:
			ApplyingFieldRules.forField_188(fieldValue, length, singleRowData, list, realMap);
			break;
		case 189:
			ApplyingFieldRules.forField_189(fieldValue, length, singleRowData, list, realMap);
			break;
		case 190:
			ApplyingFieldRules.forField_190(fieldValue, length, singleRowData, list, realMap);
			break;
		case 191:
			ApplyingFieldRules.forField_191(fieldValue, length, singleRowData, list, realMap);
			break;
		case 192:
			ApplyingFieldRules.forField_192(fieldValue, length, singleRowData, list, realMap);
			break;
		case 193:
			ApplyingFieldRules.forField_193(fieldValue, length, singleRowData, list, realMap);
			break;
		case 194:
			ApplyingFieldRules.forField_194(fieldValue, length, singleRowData, list, realMap);
			break;

		default:
			fieldValue = null;
		}
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> for_Field_1(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		// Only required for length validation
		// First check String should not contain any numeric value
		boolean alphaNumericCheck = isAlphaNumeric(fieldValue);
		boolean numericCheck = isNumeric(fieldValue);
		if (alphaNumericCheck) {
			fieldValue = "";
		} else if (numericCheck) {
			fieldValue = "";
		} else {
			fieldValue = applyRulesForLengthofField(fieldValue, length);
		}
		realMap.put("Field_1", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> for_Field_2(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap, String fieldsNumbers) {
		// Only required for length validation

		String[] arr = fieldsNumbers.split(",");
		List<String> arrPosition = Arrays.asList(arr);
		String Field_73 = "";
		String Field_20 = "";
		String Field_19 = "";
		String Field_18 = "";
		String Field_3 = "";

		if (arrPosition.contains("3")) {
			int fieldPosVal = arrPosition.indexOf("3");
			fieldPosVal = fieldPosVal + 1;
			// String positionValue = singleRowData[fieldPosVal].toString();
			if (singleRowData[fieldPosVal] != null)
				Field_3 = singleRowData[fieldPosVal].toString();
		}
		if (arrPosition.contains("73")) {
			int fieldPosVal = arrPosition.indexOf("73");
			fieldPosVal = fieldPosVal + 1;
			// String positionValue = singleRowData[fieldPosVal].toString();
			if (singleRowData[fieldPosVal] != null)
				Field_73 = singleRowData[fieldPosVal].toString();
		}
		if (arrPosition.contains("20")) {
			int fieldPosVal = arrPosition.indexOf("20");
			fieldPosVal = fieldPosVal + 1;
			// String positionValue = singleRowData[fieldPosVal].toString();
			if (singleRowData[fieldPosVal] != null)
				Field_20 = singleRowData[fieldPosVal].toString();
		}
		if (arrPosition.contains("19")) {
			int fieldPosVal = arrPosition.indexOf("19");
			fieldPosVal = fieldPosVal + 1;
			// String positionValue = singleRowData[fieldPosVal].toString();
			if (singleRowData[fieldPosVal] != null)
				Field_19 = singleRowData[fieldPosVal].toString();
		}
		if (arrPosition.contains("18")) {
			int fieldPosVal = arrPosition.indexOf("18");
			fieldPosVal = fieldPosVal + 1;
			// String positionValue = singleRowData[fieldPosVal].toString();
			if (singleRowData[fieldPosVal] != null)
				Field_18 = singleRowData[fieldPosVal].toString();
		}

		boolean alphaNumericCheck = isAlphaNumeric(fieldValue);
		boolean numericCheck = isNumeric(fieldValue);
		if (alphaNumericCheck) {
			fieldValue = "";
		} else if (numericCheck) {
			fieldValue = "";
		} else {
			fieldValue = applyRulesForLengthofField(fieldValue, length);
		}
		realMap.put("Field_2", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_3(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap, String fieldsNumbers) {

		String[] arr = fieldsNumbers.split(",");
		List<String> arrPosition = Arrays.asList(arr);
		String Field_73 = "";
		String Field_20 = "";
		String Field_19 = "";
		String Field_18 = "";
		String Field_2 = "";

		if (arrPosition.contains("2")) {
			int fieldPosVal = arrPosition.indexOf("2");
			fieldPosVal = fieldPosVal + 1;
			// String positionValue = singleRowData[fieldPosVal].toString();
			if (singleRowData[fieldPosVal] != null)
				Field_2 = singleRowData[fieldPosVal].toString();
		}
		if (arrPosition.contains("73")) {
			int fieldPosVal = arrPosition.indexOf("73");
			fieldPosVal = fieldPosVal + 1;
			// String positionValue = singleRowData[fieldPosVal].toString();
			if (singleRowData[fieldPosVal] != null)
				Field_73 = singleRowData[fieldPosVal].toString();
		}
		if (arrPosition.contains("20")) {
			int fieldPosVal = arrPosition.indexOf("20");
			fieldPosVal = fieldPosVal + 1;
			// String positionValue = singleRowData[fieldPosVal].toString();
			if (singleRowData[fieldPosVal] != null)
				Field_20 = singleRowData[fieldPosVal].toString();
		}
		if (arrPosition.contains("19")) {
			int fieldPosVal = arrPosition.indexOf("19");
			fieldPosVal = fieldPosVal + 1;
			// String positionValue = singleRowData[fieldPosVal].toString();
			if (singleRowData[fieldPosVal] != null)
				Field_19 = singleRowData[fieldPosVal].toString();
		}
		if (arrPosition.contains("18")) {
			int fieldPosVal = arrPosition.indexOf("18");
			fieldPosVal = fieldPosVal + 1;
			// String positionValue = singleRowData[fieldPosVal].toString();
			if (singleRowData[fieldPosVal] != null)
				Field_18 = singleRowData[fieldPosVal].toString();
		}

		boolean check = isAlphaNumeric(fieldValue);
		if (check) {
			fieldValue = "";
		} else {
			fieldValue = applyRulesForLengthofField(fieldValue, length);
		}
		realMap.put("Field_3", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_4(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		boolean check = isAlphaNumeric(fieldValue);
		if (check) {
			fieldValue = "";
		} else {
			fieldValue = applyRulesForLengthofField(fieldValue, length);
		}
		realMap.put("Field_4", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_5(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap, String fieldsNumbers) {
		// This field can be blank
		String[] arr = fieldsNumbers.split(",");
		List<String> arrPosition = Arrays.asList(arr);
		String Field_1 = "";
		String Field_13 = "";
		String Field_14 = "";
		String Field_20 = "";
		String Field_22 = "";
		String Field_30 = "";
		String Field_15 = "";
		String Field_16 = "";
		String Field_17 = "";
		String Field_31 = "";
		String Field_32 = "";
		String Field_33 = "";
		String Field_34 = "";
		String Field_38 = "";
		String Field_73 = "";
		String Field_77 = "";
		String Field_95 = "";

		if (arrPosition.contains("1")) {
			int fieldPosVal = arrPosition.indexOf("1");
			fieldPosVal = fieldPosVal + 1;
			// String positionValue = singleRowData[fieldPosVal].toString();
			if (singleRowData[fieldPosVal] != null)
				Field_13 = singleRowData[fieldPosVal].toString();
		}
		if (arrPosition.contains("13")) {
			int fieldPosVal = arrPosition.indexOf("13");
			fieldPosVal = fieldPosVal + 1;
			// String positionValue = singleRowData[fieldPosVal].toString();
			if (singleRowData[fieldPosVal] != null)
				Field_13 = singleRowData[fieldPosVal].toString();
		}
		if (arrPosition.contains("14")) {
			int fieldPosVal = arrPosition.indexOf("1");
			fieldPosVal = fieldPosVal + 1;
			// String positionValue = singleRowData[fieldPosVal].toString();
			if (singleRowData[fieldPosVal] != null)
				Field_14 = singleRowData[fieldPosVal].toString();
		}
		if (arrPosition.contains("15")) {
			int fieldPosVal = arrPosition.indexOf("15");
			fieldPosVal = fieldPosVal + 1;
			// String positionValue = singleRowData[fieldPosVal].toString();
			if (singleRowData[fieldPosVal] != null)
				Field_15 = singleRowData[fieldPosVal].toString();
		}
		if (arrPosition.contains("16")) {
			int fieldPosVal = arrPosition.indexOf("16");
			fieldPosVal = fieldPosVal + 1;
			// String positionValue = singleRowData[fieldPosVal].toString();
			if (singleRowData[fieldPosVal] != null)
				Field_16 = singleRowData[fieldPosVal].toString();
		}
		if (arrPosition.contains("17")) {
			int fieldPosVal = arrPosition.indexOf("17");
			fieldPosVal = fieldPosVal + 1;
			// String positionValue = singleRowData[fieldPosVal].toString();
			if (singleRowData[fieldPosVal] != null)
				Field_17 = singleRowData[fieldPosVal].toString();
		}
		if (arrPosition.contains("20")) {
			int fieldPosVal = arrPosition.indexOf("20");
			fieldPosVal = fieldPosVal + 1;
			// String positionValue = singleRowData[fieldPosVal].toString();
			if (singleRowData[fieldPosVal] != null)
				Field_20 = singleRowData[fieldPosVal].toString();
		}
		if (arrPosition.contains("22")) {
			int fieldPosVal = arrPosition.indexOf("22");
			fieldPosVal = fieldPosVal + 1;
			// String positionValue = singleRowData[fieldPosVal].toString();
			if (singleRowData[fieldPosVal] != null)
				Field_22 = singleRowData[fieldPosVal].toString();
		}
		if (arrPosition.contains("32")) {
			int fieldPosVal = arrPosition.indexOf("32");
			fieldPosVal = fieldPosVal + 1;
			// String positionValue = singleRowData[fieldPosVal].toString();
			if (singleRowData[fieldPosVal] != null)
				Field_32 = singleRowData[fieldPosVal].toString();
		}
		if (arrPosition.contains("31")) {
			int fieldPosVal = arrPosition.indexOf("31");
			fieldPosVal = fieldPosVal + 1;
			// String positionValue = singleRowData[fieldPosVal].toString();
			if (singleRowData[fieldPosVal] != null)
				Field_31 = singleRowData[fieldPosVal].toString();
		}
		if (arrPosition.contains("33")) {
			int fieldPosVal = arrPosition.indexOf("33");
			fieldPosVal = fieldPosVal + 1;
			// String positionValue = singleRowData[fieldPosVal].toString();
			if (singleRowData[fieldPosVal] != null)
				Field_33 = singleRowData[fieldPosVal].toString();
		}
		if (arrPosition.contains("34")) {
			int fieldPosVal = arrPosition.indexOf("34");
			fieldPosVal = fieldPosVal + 1;
			// String positionValue = singleRowData[fieldPosVal].toString();
			if (singleRowData[fieldPosVal] != null)
				Field_34 = singleRowData[fieldPosVal].toString();
		}
		if (arrPosition.contains("73")) {
			int fieldPosVal = arrPosition.indexOf("73");
			fieldPosVal = fieldPosVal + 1;
			// String positionValue = singleRowData[fieldPosVal].toString();
			if (singleRowData[fieldPosVal] != null)
				Field_73 = singleRowData[fieldPosVal].toString();
		}
		if (arrPosition.contains("38")) {
			int fieldPosVal = arrPosition.indexOf("38");
			fieldPosVal = fieldPosVal + 1;
			// String positionValue = singleRowData[fieldPosVal].toString();
			if (singleRowData[fieldPosVal] != null)
				Field_38 = singleRowData[fieldPosVal].toString();
		}
		if (arrPosition.contains("95")) {
			int fieldPosVal = arrPosition.indexOf("95");
			fieldPosVal = fieldPosVal + 1;
			// String positionValue = singleRowData[fieldPosVal].toString();
			if (singleRowData[fieldPosVal] != null)
				Field_95 = singleRowData[fieldPosVal].toString();
		}
		if (arrPosition.contains("77")) {
			int fieldPosVal = arrPosition.indexOf("77");
			fieldPosVal = fieldPosVal + 1;
			// String positionValue = singleRowData[fieldPosVal].toString();
			if (singleRowData[fieldPosVal] != null)
				Field_77 = singleRowData[fieldPosVal].toString();
		}
		if (Field_13 != null || !Field_13.equals("") || !Field_13.isEmpty()) {
			Field_13 = replaceMoreThanOneSpaceWithSingleSpace(Field_13);
			Field_30 = replaceMoreThanOneSpaceWithSingleSpace(Field_30);
			if (Field_13.length() >= 10) {
				Field_13 = Field_13.substring(0, 9);
			}
			if (Field_30.length() >= 10) {
				Field_30 = Field_30.substring(0, 9);
			}
			if (Field_13.equalsIgnoreCase(Field_30)) {
				if (Field_15.equals("") || Field_16.equals("") || Field_17.equals("")) {
					Field_1 = replaceMoreThanOneSpaceWithSingleSpace(Field_1);
					Field_32 = replaceMoreThanOneSpaceWithSingleSpace(Field_32);
					if (Field_32.equals(Field_1)) {
						String[] cityArray = MappingConstants.cityArray;
						List<String> cityList = Arrays.asList(cityArray);
						if (cityList.contains(Field_31)) {
							Field_14 = Field_31;
							Field_15 = Field_32;
							Field_16 = Field_33;
							Field_17 = Field_34;
							fieldValue = "P";
						}
					}
				}
			}
		} else {
			Field_13 = replaceMoreThanOneSpaceWithSingleSpace(Field_13);
			Field_73 = Field_73.substring(0, 1);
			if (isNumeric(Field_73)) {
				if (Integer.parseInt(Field_38) > 0 || Integer.parseInt(Field_95) > 0
						|| Integer.parseInt(Field_77) > 0) {
					Field_30 = Field_30.substring(0, 1);
					if (isNumeric(Field_30)) {
						String[] cityArray = MappingConstants.cityArray;
						List<String> cityList = Arrays.asList(cityArray);
						if (cityList.contains(Field_31)) {
							if (Field_32.equalsIgnoreCase(Field_1) && (Field_22 == null || Field_22.equals("")
									&& (!Field_20.equalsIgnoreCase("CO") || Field_20.equalsIgnoreCase("ES")
											|| Field_20.equalsIgnoreCase("PA") || Field_20.equalsIgnoreCase("GV")))) {
								Field_13 = Field_30;
								Field_14 = Field_31;
								Field_15 = Field_32;
								Field_16 = Field_33;
								Field_17 = Field_34;
								fieldValue = "B";
							}
						}
					}
				}
			}
		}
		/*
		 * boolean check = isAlphaNumeric(fieldValue); if (check) { fieldValue =
		 * ""; } else { fieldValue = applyRulesForLengthofField(fieldValue,
		 * length); }
		 */
		realMap.put("Field_5", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_6(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		boolean check = isAlphaNumeric(fieldValue);
		if (check) {
			fieldValue = "";
		} else {
			fieldValue = applyRulesForLengthofField(fieldValue, length);
		}
		realMap.put("Field_6", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_7(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		boolean check = isAlphaNumeric(fieldValue);
		if (check) {
			fieldValue = "";
		} else {
			fieldValue = applyRulesForLengthofField(fieldValue, length);
		}
		realMap.put("Field_7", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_8(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		boolean check = isAlphaNumeric(fieldValue);
		if (check) {
			fieldValue = "";
		} else {
			fieldValue = applyRulesForLengthofField(fieldValue, length);
		}
		realMap.put("Field_8", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_9(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		boolean check = isAlphaNumeric(fieldValue);
		if (check) {
			fieldValue = "";
		} else {
			fieldValue = applyRulesForLengthofField(fieldValue, length);
		}
		realMap.put("Field_9", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_10(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		boolean check = isAlphaNumeric(fieldValue);
		if (check) {
			fieldValue = "";
		} else {
			fieldValue = applyRulesForLengthofField(fieldValue, length);
		}
		realMap.put("Field_10", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_11(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		boolean check = isAlphaNumeric(fieldValue);
		if (check) {
			fieldValue = "";
		} else {
			fieldValue = applyRulesForLengthofField(fieldValue, length);
		}
		realMap.put("Field_11", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_12(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		boolean check = isAlphaNumeric(fieldValue);
		if (check) {
			fieldValue = "";
		} else {
			fieldValue = applyRulesForLengthofField(fieldValue, length);
		}
		realMap.put("Field_12", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_13(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		boolean check = isAlphaNumeric(fieldValue);
		if (check) {
			fieldValue = "";
		} else {
			fieldValue = applyRulesForLengthofField(fieldValue, length);
		}
		realMap.put("Field_13", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_14(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		boolean check = isAlphaNumeric(fieldValue);
		if (check) {
			fieldValue = "";
		} else {
			fieldValue = applyRulesForLengthofField(fieldValue, length);
		}
		realMap.put("Field_14", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_15(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		boolean check = isAlphaNumeric(fieldValue);
		if (check) {
			fieldValue = "";
		} else {
			fieldValue = applyRulesForLengthofField(fieldValue, length);
		}
		realMap.put("Field_15", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_16(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		boolean check = isAlphaNumeric(fieldValue);
		if (check) {
			fieldValue = "";
		} else {
			fieldValue = applyRulesForLengthofField(fieldValue, length);
		}
		realMap.put("Field_16", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_17(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_17", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_18(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap,Map<String, String> dummyValueHoldingMap) {
		//boolean check = isAlphaNumeric(fieldValue);
		dummyValueHoldingMap.put("Field_18_value",fieldValue);
		boolean check = isAlpha(fieldValue);
		try{
			if (!check) {
				throw new Exception("Field 18 cannot be null");
			} else
			{
				String fnlStrgOfFld_18 = "";
				String restOfStrgOfFld_18 = "";
				if (fieldValue.length() > length) {
					int index = 0;
					String stringTillLength = fieldValue.substring(0, length);
					String stringAfterLength = fieldValue.substring(length, fieldValue.length());
					char ch = fieldValue.charAt(length - 2);
					char ch1 = fieldValue.charAt(length);
					if (ch == ' ') {
						index = length - 2;
						fnlStrgOfFld_18 = fieldValue.substring(0, index);
					} else {
						if (ch1 == ' ') {
							fnlStrgOfFld_18 = fieldValue.substring(0, length);
							System.out.println(fieldValue);
						} else {
							for (int i = stringTillLength.length() - 1; i >= 0; i--) {
								char ch2 = fieldValue.charAt(i);
								index = i;
								fnlStrgOfFld_18 = fieldValue.substring(0, index);
								if (ch2 == ' ')
									break;
							}
						}
					}
					char ch3 = fieldValue.charAt(fnlStrgOfFld_18.length());
					if (ch3 == ' ') {
						restOfStrgOfFld_18 = fieldValue.substring(fnlStrgOfFld_18.length() + 1, fieldValue.length());
					} else {
						restOfStrgOfFld_18 = fieldValue.substring(fnlStrgOfFld_18.length(), fieldValue.length());
					}
					dummyValueHoldingMap.put("restOfStrgOfFld_18", restOfStrgOfFld_18);
					fieldValue = fnlStrgOfFld_18;
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		realMap.put("Field_18", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_19(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap, String fieldsNumbers,Map<String, String> dummyValueHoldingMap) {
		// First check length of 18 field is greater than 80
		try
		{
			String Field_18_value = dummyValueHoldingMap.get("Field_18_value");
			String Field_19_value = fieldValue;
			String newFieldValue = "";
			boolean check = isAlpha(fieldValue);
			if (check) {
				throw new Exception("Field 19 cannot be null");
			} else {
				String restOfStrgOfFld_18 = dummyValueHoldingMap.get("restOfStrgOfFld_18");
				
				fieldValue = replaceMoreThanOneSpaceWithSingleSpace(fieldValue);
				fieldValue = restOfStrgOfFld_18 + "; "+fieldValue;
				System.out.println("fieldValue = " + fieldValue);
				
				String fnlStrgOfFld_19 = "";
				String restOfStrgOfFld_19 = "";
				if (fieldValue.length() > length) {
					int index = 0;
					String stringTillLength = fieldValue.substring(0, length);
					String stringAfterLength = fieldValue.substring(length, fieldValue.length());
					char ch = fieldValue.charAt(length - 2);
					char ch1 = fieldValue.charAt(length);
					if (ch == ' ') {
						index = length - 2;
						fnlStrgOfFld_19 = fieldValue.substring(0, index);
					} else {
						if (ch1 == ' ') {
							fnlStrgOfFld_19 = fieldValue.substring(0, length);
							System.out.println(fieldValue);
						} else {
							for (int i = stringTillLength.length() - 1; i >= 0; i--) {
								char ch2 = fieldValue.charAt(i);
								index = i;
								fnlStrgOfFld_19 = fieldValue.substring(0, index);
								if (ch2 == ' ')
									break;
							}
						}
					}
					char ch3 = fieldValue.charAt(fnlStrgOfFld_19.length());
					if (ch3 == ' ') {
						restOfStrgOfFld_19 = fieldValue.substring(fnlStrgOfFld_19.length() + 1, fieldValue.length());
					} else {
						restOfStrgOfFld_19 = fieldValue.substring(fnlStrgOfFld_19.length(), fieldValue.length());
					}
					fieldValue = restOfStrgOfFld_19;
				   if(!restOfStrgOfFld_19.isEmpty())
				   {
					   System.out.println("Create new field");
					   newFieldValue = Field_18_value + " "+Field_19_value;
				   }
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		realMap.put("Field_19", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_20(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_20", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_21(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_21", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_22(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_22", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_23(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_23", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_24(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_24", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_25(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_25", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_26(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_26", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_27(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_27", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_28(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_28", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_29(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_29", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_30(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_30", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_31(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_31", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_32(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_32", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_33(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_33", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_34(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_34", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_35(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		if (singleRowData[71] != null) {
			String nextFieldValue = singleRowData[71].toString();
			if (nextFieldValue.equals(fieldValue)) {
				fieldValue = null;
			}
		}
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_35", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_36(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_36", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_37(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		if (fieldValue == "" || fieldValue == null) {
			fieldValue = "0000000000";
		}
		boolean b = isNumeric(fieldValue);
		if (b) {
			fieldValue = applyRulesForLengthofField(fieldValue, length);
		}
		realMap.put("Field_37", fieldValue);
		// fieldValue = applyRulesForLengthofField(fieldValue, length);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_38(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		if (fieldValue == "" || fieldValue == null) {
			fieldValue = "0000000000";
		}
		boolean b = isNumeric(fieldValue);
		if (b) {
			fieldValue = applyRulesForLengthofField(fieldValue, length);
		}
		realMap.put("Field_38", fieldValue);
		// fieldValue = applyRulesForLengthofField(fieldValue, length);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_39(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap, String fieldsNumbers) {
		try {
			int fieldPosVal = 0;
			String positionValue = "";
			String[] arr = fieldsNumbers.split(",");
			List<String> arrPosition = Arrays.asList(arr);
			if (fieldValue == null || fieldValue.isEmpty()) {
				fieldValue = "00000000000";
			}
			if (arrPosition.contains("37") && arrPosition.contains("38")) {
				if (field_37_value > 0 || field_38_value > 0) {
					int sum = field_37_value + field_38_value;
					fieldValue = String.valueOf(sum);
				}
			} else {
				fieldValue = "00000000000";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		realMap.put("Field_39", fieldValue);
		// fieldValue = applyRulesForLengthofField(fieldValue, length);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_40(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		// done
		try {
			boolean numericCheck = isNumeric(fieldValue);
			if (numericCheck) {
				if (fieldValue.length() == 4) {
					Date date = new Date();
					Calendar cal = Calendar.getInstance();
					cal.setTime(date);
					int year = cal.get(Calendar.YEAR);
					int month = cal.get(Calendar.MONTH);
					int day = cal.get(Calendar.DAY_OF_MONTH);

					if (Integer.parseInt(fieldValue) >= 1637 && Integer.parseInt(fieldValue) <= year) {
					} else {
						throw new Exception("Field 40 is not valid year=" + fieldValue);
					}
				} else {
					throw new Exception("Field 40 have more than 4 digits=" + fieldValue);
				}
				// fieldValue = applyRulesForLengthofField(fieldValue, length);
			} else {
				throw new Exception("Field 40 have not numeric=" + fieldValue);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		realMap.put("Field_40", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_41(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_41", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_42(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_42", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_43(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_43", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_44(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_44", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_45(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_45", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_46(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_46", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_47(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap, String fieldsNumbers) {
		// If 47 field value is null then take value from Legal Brief
		// Description(Field=069)
		if (fieldValue == null) {
			// String positionValue = "";
			String[] arr = fieldsNumbers.split(",");
			List<String> arrPosition = Arrays.asList(arr);
			if (arrPosition.contains("69")) {
				int fieldPosVal = arrPosition.indexOf("69");
				fieldPosVal = fieldPosVal + 1;
				if (singleRowData[fieldPosVal] != null) {
					fieldValue = singleRowData[fieldPosVal].toString();
					fieldValue = replaceMoreThanOneSpaceWithSingleSpace(fieldValue);
				} else {
					fieldValue = "";
				}
				// String[] split=positionValue.split(" ");
			}
		}
		// Check for special character, there is only hyphen '-' will be there
		// for special character
		fieldValue = removeSpecialCharactersExpectHyphen(fieldValue);
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_47", fieldValue);
		return realMap;
		// return realMap;
		// completed
	}

	public static Map<String, String> forField_48(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap, String fieldsNumbers) {
		if (fieldValue == null) {
			// String positionValue = "";
			String[] arr = fieldsNumbers.split(",");
			List<String> arrPosition = Arrays.asList(arr);
			if (arrPosition.contains("69")) {
				int fieldPosVal = arrPosition.indexOf("69");
				fieldPosVal = fieldPosVal + 1;
				if (singleRowData[fieldPosVal] != null) {
					fieldValue = singleRowData[fieldPosVal].toString();
					fieldValue = replaceMoreThanOneSpaceWithSingleSpace(fieldValue);
				} else {
					fieldValue = "";
				}
				// String[] split=positionValue.split(" ");
			}
		}
		// Remove all special characters from fild value
		fieldValue = removeAllSpecialCharacters(fieldValue);
		// If it start with 0 then trim 0
		String zeroCheck = fieldValue.substring(0, 1);
		boolean check = isNumeric(zeroCheck);
		if (check) {
			if (Integer.parseInt(zeroCheck) == 0)
				fieldValue = fieldValue.substring(1, fieldValue.length());
		}
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_48", fieldValue);
		return realMap;
		// return realMap;
		// completed
	}

	public static Map<String, String> forField_49(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap, String fieldsNumbers) {
		if (fieldValue == null) {
			// String positionValue = "";
			String[] arr = fieldsNumbers.split(",");
			List<String> arrPosition = Arrays.asList(arr);
			if (arrPosition.contains("69")) {
				int fieldPosVal = arrPosition.indexOf("69");
				fieldPosVal = fieldPosVal + 1;
				if (singleRowData[fieldPosVal] != null) {
					fieldValue = singleRowData[fieldPosVal].toString();
					fieldValue = replaceMoreThanOneSpaceWithSingleSpace(fieldValue);
				} else {
					fieldValue = "";
				}
				// String[] split=positionValue.split(" ");
			}
		}
		// Remove all special characters from fild value
		fieldValue = removeAllSpecialCharacters(fieldValue);
		// If it start with 0 then trim 0
		String zeroCheck = fieldValue.substring(0, 1);
		boolean check = isNumeric(zeroCheck);
		if (check) {
			if (Integer.parseInt(zeroCheck) == 0)
				fieldValue = fieldValue.substring(1, fieldValue.length());
		}
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		String field_48 = realMap.get("Field_48");
		if (field_48.equals("") || field_48.isEmpty()) {
			fieldValue = "";
		}
		realMap.put("Field_49", fieldValue);
		return realMap;
		// return realMap;
		// not completed. Regular expression validation is left
	}

	public static Map<String, String> forField_50(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		// It should be numeric only or can be blank
		boolean numericCheck = isNumeric(fieldValue);
		boolean alphaNumericCheck = isAlphaNumeric(fieldValue);
		if (numericCheck) {
			String firstFourDigit = "";
			// Check for first 4 digit, if it is 0000 the set as blank
			if (fieldValue.equals("01011900")) {
				fieldValue = "";
			}
			if (fieldValue.length() >= 4) {
				firstFourDigit = fieldValue.substring(0, 4);
				if (firstFourDigit.equals("0000")) {
					fieldValue = "";
				}
			}
			if (fieldValue.length() == 6) {
				String month = fieldValue.substring(0, 2);
				String year = fieldValue.substring(2, 6);
				// Checking date and year is valid of not
				Calendar cal = Calendar.getInstance();
				Date today = cal.getTime();
				cal.add(Calendar.YEAR, 5); // to get previous year add -1
				Date nextYear = cal.getTime();
				int currentYear = cal.get(Calendar.YEAR);
				// boolean valiDate = validateDateMonthYear(day, month, year,
				// yearFrom, yearTo);
				if (Integer.parseInt(year) >= 1637 && Integer.parseInt(year) <= currentYear) {
					if (Integer.parseInt(month) >= 1 && Integer.parseInt(month) <= 12) {
						fieldValue = applyRulesForLengthofField(fieldValue, length);
					} else {
						fieldValue = "";
					}
				} else {
					fieldValue = "";
				}
			} else if (fieldValue.length() == 8) {
				String day = fieldValue.substring(0, 2);
				String month = fieldValue.substring(2, 4);
				String year = fieldValue.substring(4, 8);
				// Checking date and year is valid of not
				Calendar cal = Calendar.getInstance();
				Date today = cal.getTime();
				cal.add(Calendar.YEAR, 5); // to get previous year add -1
				Date nextYear = cal.getTime();
				int currentYear = cal.get(Calendar.YEAR);
				boolean valiDate = validateDateMonthYear(Integer.parseInt(day), Integer.parseInt(month),
						Integer.parseInt(year), 1637, currentYear);
				if (valiDate) {
					fieldValue = applyRulesForLengthofField(fieldValue, length);
				} else {
					fieldValue = "";
				}
			}
		} else if (alphaNumericCheck) {
			// Perform different logic
			String year = fieldValue.substring(fieldValue.length() - 4, fieldValue.length());
			System.out.println(year);
			Calendar cal = Calendar.getInstance();
			Date today = cal.getTime();
			cal.add(Calendar.YEAR, 5); // to get previous year add -1
			Date nextYear = cal.getTime();
			int currentYear = cal.get(Calendar.YEAR);
			if (Integer.parseInt(year) >= 1637 && Integer.parseInt(year) <= currentYear) {
				String restDate = fieldValue.substring(0, fieldValue.length() - 4);
				restDate = removeAllSpecialCharacters(restDate);
				boolean check1 = isAlphaNumeric(restDate);
				if (check1) {
					// Get all integer value from restDate
					String day = restDate.replaceAll("[^0-9]+", " ");
					day = removeAllSpecialCharacters(day);
					// Get all characters fro alphanumeric string
					String month = restDate.replaceAll("[0-9]", "");
					month = removeAllSpecialCharacters(month);
					// String day =
					// fieldValue.substring(restDate.length()-2,restDate.length()+1);
					// String month =
					// fieldValue.substring(0,restDate.length()-2);
					String finalDate = month + " " + day + "," + " " + year;
					try {
						Date strToDate = DateFormat.getDateInstance().parse(finalDate);
						// Date nextYear1 = cal.getTime();
						strToDate = cal.getTime();
						int m = cal.get(Calendar.MONTH);
						// int m = strToDate.getMonth();
						m = m + 1;
						String mon = String.valueOf(m);
						if (mon.length() < 2) {
							mon = "0" + mon;
						}
						if (day.length() < 2) {
							day = "0" + day;
						}
						fieldValue = mon + day + year;
					} catch (ParseException e) {
						fieldValue = "";
					}
				} else {
					try {
						restDate = restDate + " 03, " + year;
						Date strToDate = DateFormat.getDateInstance().parse(restDate);
						int m = strToDate.getMonth();
						m = m + 1;
						String mon = String.valueOf(m);
						if (mon.length() < 2) {
							mon = "0" + mon;
						}
						fieldValue = mon + "00" + year;
					} catch (ParseException e) {
						fieldValue = "";
					}
				}
			}
		} else {
			fieldValue = "";
		}
		// fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_50", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_51(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = removeAllSpecialCharacters(fieldValue);
		boolean isAlpha = isAlpha(fieldValue);
		if (isAlpha) {
			fieldValue = applyRulesForLengthofField(fieldValue, length);
		} else {
			fieldValue = "";
		}
		realMap.put("Field_51", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_52(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap, String fieldsNumbers) {
		try {
			String positionValue = "";
			String[] arr = fieldsNumbers.split(",");
			List<String> arrPosition = Arrays.asList(arr);
			if (arrPosition.contains("50")) {
				int fieldPosVal = arrPosition.indexOf("50");
				fieldPosVal = fieldPosVal + 1;
				if (singleRowData[fieldPosVal] != null)
					positionValue = singleRowData[fieldPosVal].toString();
			}
			boolean numCheck = isNumeric(fieldValue);
			if (numCheck) {
				int salePrice = Integer.parseInt(fieldValue);
				if (positionValue != null || !positionValue.isEmpty()) {
					if (salePrice < 500) {
						fieldValue = "0000000000";
					} else {
						fieldValue = applyRulesForLengthofField(fieldValue, length);
					}
				}
			} else {
				throw new Exception("Field 52 value is not numeric");
			}
		} catch (Exception e) {

		}
		// fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_52", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_53(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap, String fieldsNumbers) {
		String positionValue = "";
		String[] arr = fieldsNumbers.split(",");
		List<String> arrPosition = Arrays.asList(arr);
		if (arrPosition.contains("52")) {
			int fieldPosVal = arrPosition.indexOf("52");
			fieldPosVal = fieldPosVal + 1;
			if (singleRowData[fieldPosVal] != null)
				positionValue = singleRowData[fieldPosVal].toString();
		}
		if (positionValue != null || !positionValue.isEmpty()) {
			if (!positionValue.equals("0000000000") || Integer.parseInt(positionValue) > 0) {
				if (fieldValue.equals("D") || fieldValue.equals("F") || fieldValue.equals("P") || fieldValue.equals("U")
						|| fieldValue.equals("X") || fieldValue.equals("Q")) {
					fieldValue = "A";
				}
			}
		}
		// fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_53", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_54(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		// It should be numeric only or can be blank
		boolean numericCheck = isNumeric(fieldValue);
		boolean alphaNumericCheck = isAlphaNumeric(fieldValue);
		if (numericCheck) {
			String firstFourDigit = "";
			// Check for first 4 digit, if it is 0000 the set as blank
			if (fieldValue.equals("01011900")) {
				fieldValue = "";
			}
			if (fieldValue.length() >= 4) {
				firstFourDigit = fieldValue.substring(0, 4);
				if (firstFourDigit.equals("0000")) {
					fieldValue = "";
				}
			}
			if (fieldValue.length() == 6) {
				String month = fieldValue.substring(0, 2);
				String year = fieldValue.substring(2, 6);
				// Checking date and year is valid of not
				Calendar cal = Calendar.getInstance();
				Date today = cal.getTime();
				cal.add(Calendar.YEAR, 5); // to get previous year add -1
				Date nextYear = cal.getTime();
				int currentYear = cal.get(Calendar.YEAR);
				// boolean valiDate = validateDateMonthYear(day, month, year,
				// yearFrom, yearTo);
				if (Integer.parseInt(year) >= 1637 && Integer.parseInt(year) <= currentYear) {
					if (Integer.parseInt(month) >= 1 && Integer.parseInt(month) <= 12) {
						fieldValue = applyRulesForLengthofField(fieldValue, length);
					} else {
						fieldValue = "";
					}
				} else {
					fieldValue = "";
				}
			} else if (fieldValue.length() == 8) {
				String day = fieldValue.substring(0, 2);
				String month = fieldValue.substring(2, 4);
				String year = fieldValue.substring(4, 8);
				// Checking date and year is valid of not
				Calendar cal = Calendar.getInstance();
				Date today = cal.getTime();
				cal.add(Calendar.YEAR, 5); // to get previous year add -1
				Date nextYear = cal.getTime();
				int currentYear = cal.get(Calendar.YEAR);
				boolean valiDate = validateDateMonthYear(Integer.parseInt(day), Integer.parseInt(month),
						Integer.parseInt(year), 1637, currentYear);
				if (valiDate) {
					fieldValue = applyRulesForLengthofField(fieldValue, length);
				} else {
					fieldValue = "";
				}
			}
		} else if (alphaNumericCheck) {
			// Perform different logic
			String year = fieldValue.substring(fieldValue.length() - 4, fieldValue.length());
			System.out.println(year);
			Calendar cal = Calendar.getInstance();
			Date today = cal.getTime();
			cal.add(Calendar.YEAR, 5); // to get previous year add -1
			Date nextYear = cal.getTime();
			int currentYear = cal.get(Calendar.YEAR);
			if (Integer.parseInt(year) >= 1637 && Integer.parseInt(year) <= currentYear) {
				String restDate = fieldValue.substring(0, fieldValue.length() - 4);
				restDate = removeAllSpecialCharacters(restDate);
				boolean check1 = isAlphaNumeric(restDate);
				if (check1) {
					// Get all integer value from restDate
					String day = restDate.replaceAll("[^0-9]+", " ");
					day = removeAllSpecialCharacters(day);
					// Get all characters fro alphanumeric string
					String month = restDate.replaceAll("[0-9]", "");
					month = removeAllSpecialCharacters(month);
					// String day =
					// fieldValue.substring(restDate.length()-2,restDate.length()+1);
					// String month =
					// fieldValue.substring(0,restDate.length()-2);
					String finalDate = month + " " + day + "," + " " + year;
					try {
						Date strToDate = DateFormat.getDateInstance().parse(finalDate);
						// Date nextYear1 = cal.getTime();
						strToDate = cal.getTime();
						int m = cal.get(Calendar.MONTH);
						// int m = strToDate.getMonth();
						m = m + 1;
						String mon = String.valueOf(m);
						if (mon.length() < 2) {
							mon = "0" + mon;
						}
						if (day.length() < 2) {
							day = "0" + day;
						}
						fieldValue = mon + day + year;
					} catch (ParseException e) {
						fieldValue = "";
					}
				} else {
					try {
						restDate = restDate + " 03, " + year;
						Date strToDate = DateFormat.getDateInstance().parse(restDate);
						int m = strToDate.getMonth();
						m = m + 1;
						String mon = String.valueOf(m);
						if (mon.length() < 2) {
							mon = "0" + mon;
						}
						fieldValue = mon + "00" + year;
					} catch (ParseException e) {
						fieldValue = "";
					}
				}
			}
		} else {
			fieldValue = "";
		}
		realMap.put("Field_54", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_55(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap, String fieldsNumbers) {
		try {
			String positionValue = "";
			String[] arr = fieldsNumbers.split(",");
			List<String> arrPosition = Arrays.asList(arr);
			if (arrPosition.contains("54")) {
				int fieldPosVal = arrPosition.indexOf("54");
				fieldPosVal = fieldPosVal + 1;
				if (singleRowData[fieldPosVal] != null)
					positionValue = singleRowData[fieldPosVal].toString();
			}
			boolean numCheck = isNumeric(fieldValue);
			if (numCheck) {
				int salePrice = Integer.parseInt(fieldValue);
				if (positionValue != null || !positionValue.isEmpty()) {
					if (salePrice < 500) {
						fieldValue = "0000000000";
					} else {
						fieldValue = applyRulesForLengthofField(fieldValue, length);
					}
				}
			} else {
				throw new Exception("Field 52 value is not numeric");
			}
		} catch (Exception e) {

		}
		realMap.put("Field_55", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_56(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap, String fieldsNumbers) {
		String positionValue = "";
		String[] arr = fieldsNumbers.split(",");
		List<String> arrPosition = Arrays.asList(arr);
		if (arrPosition.contains("55")) {
			int fieldPosVal = arrPosition.indexOf("55");
			fieldPosVal = fieldPosVal + 1;
			if (singleRowData[fieldPosVal] != null)
				positionValue = singleRowData[fieldPosVal].toString();
		}
		if (positionValue == null || positionValue.isEmpty()) {
			fieldValue = "";
		} else {
			if (!positionValue.equals("0000000000") || Integer.parseInt(positionValue) > 0) {
				if (fieldValue.equals("D") || fieldValue.equals("F") || fieldValue.equals("P") || fieldValue.equals("U")
						|| fieldValue.equals("X") || fieldValue.equals("Q")) {
					fieldValue = "A";
				}
			}
		}
		realMap.put("Field_56", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_57(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap, String fieldsNumbers) {
		int LTCount = 0;
		int PTCount = 0;
		Map<String, Integer> lTPTCount = new HashMap<String, Integer>();
		String[] arr = fieldsNumbers.split(",");
		List<String> arrPosition = Arrays.asList(arr);
		if (arrPosition.contains("69")) {
			int fieldPosVal = arrPosition.indexOf("69");
			fieldPosVal = fieldPosVal + 1;
			if (singleRowData[fieldPosVal] != null) {
				String positionValue = singleRowData[fieldPosVal].toString();
				fieldValue = positionValue;
			}
		}	
		/*if(fieldValue == null || fieldValue.isEmpty())
		{
			String[] arr = fieldsNumbers.split(",");
			List<String> arrPosition = Arrays.asList(arr);
			if (arrPosition.contains("69")) {
				int fieldPosVal = arrPosition.indexOf("69");
				fieldPosVal = fieldPosVal + 1;
				if (singleRowData[fieldPosVal] != null) {
					String positionValue = singleRowData[fieldPosVal].toString();
					fieldValue = positionValue;
				}
			}	
		}*/
		if (fieldValue.contains("LOTS") || fieldValue.contains("LOT") || fieldValue.contains("LTS")
				|| fieldValue.contains("LT")) {
			// replace all double or more than double spaces from single spac
			fieldValue = replaceMoreThanOneSpaceWithSingleSpace(fieldValue);
			// count number of lots
			String[] lotsCount = fieldValue.split(" ");
			for (int i = 0; i < lotsCount.length; i++) {
				lotsCount[i] = removeAllSpecialCharacters(lotsCount[i]);
				if (!isNumeric(lotsCount[i])) {
					if (lTPTCount.containsKey(lotsCount[i])) {
						lTPTCount.put(lotsCount[i], lTPTCount.get(lotsCount[i]) + 1);
					} else {
						lTPTCount.put(lotsCount[i], 1);
					}
				}
			}
			if(lTPTCount.get("LOTS") == null)
			{
				lTPTCount.put("LOTS", 0);
			}
			if(lTPTCount.get("LOT") == null)
			{
				lTPTCount.put("LOT", 0);
			}
			if(lTPTCount.get("LT") == null)
			{
				lTPTCount.put("LT", 0);
			}
			if(lTPTCount.get("LTS") == null)
			{
				lTPTCount.put("LTS", 0);
			}
			if(lTPTCount.get("PT") == null)
			{
				lTPTCount.put("PT", 0);
			}
			if(lTPTCount.get("PORTION") == null)
			{
				lTPTCount.put("PORTION", 0);
			}
			LTCount = lTPTCount.get("LOTS") + lTPTCount.get("LOT") + lTPTCount.get("LTS") + lTPTCount.get("LT");
			PTCount = lTPTCount.get("PT") + lTPTCount.get("PORTION") ;
			System.out.println(LTCount);
			System.out.println(PTCount);
		}
		/*int LTCount = 0;
		int PTCount = 0;
		String positionValue = "";
		String[] arr = fieldsNumbers.split(",");
		List<String> arrPosition = Arrays.asList(arr);
		if (arrPosition.contains("58")) {
			int fieldPosVal = arrPosition.indexOf("58");
			fieldPosVal = fieldPosVal + 1;
			if (singleRowData[fieldPosVal] != null)
				positionValue = singleRowData[fieldPosVal].toString();
			// Used to replace all double or more spaces with single space from
			// string
			positionValue = replaceMoreThanOneSpaceWithSingleSpace(positionValue);
			String[] split = positionValue.split(" ");

			for (int i = 0; i < split.length; i++) {
				System.out.println(split[i].toString());
				if (split[i].toString().equals("LOTS") || split[i].contains("LOT") || split[i].contains("LTS")
						|| split[i].contains("LT")) {
					LTCount = LTCount + 1;
				} else if (split[i].toString().equals("PT") || split[i].toString().equals("Portion")) {
					PTCount = PTCount + 1;
				}
			}*/
			if (LTCount == 1 && PTCount < 1) {
				fieldValue = "";
			} else if (LTCount == 1 && PTCount >= 1) {
				fieldValue = "P";
			} else if (LTCount > 1 && PTCount >= 1) {
				fieldValue = "MP";
			}
		
		// fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_57", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_58(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap, String fieldsNumbers) {
//check that field_58 value is exist or not. If  not then get from legal breif description[69] field.
		if(fieldValue == null || fieldValue.isEmpty())
		{
			String[] arr = fieldsNumbers.split(",");
			List<String> arrPosition = Arrays.asList(arr);
			if (arrPosition.contains("69")) {
				int fieldPosVal = arrPosition.indexOf("69");
				fieldPosVal = fieldPosVal + 1;
				if (singleRowData[fieldPosVal] != null) {
					String positionValue = singleRowData[fieldPosVal].toString();
					fieldValue = positionValue;
				}
			}	
		}
		
		// fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_58", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_59(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap, String fieldsNumbers) {
		String positionValue = "";
		Pattern special = Pattern.compile("[!@#$%*()_+=|<>?{}\\[\\]~-]");
		String[] arr = fieldsNumbers.split(",");
		List<String> arrPosition = Arrays.asList(arr);
		if (arrPosition.contains("57")) {
			int fieldPosVal = arrPosition.indexOf("57");
			fieldPosVal = fieldPosVal + 1;
			if (singleRowData[fieldPosVal] != null)
				positionValue = singleRowData[fieldPosVal].toString();
			if (positionValue == null || positionValue.isEmpty()) {
				fieldValue = "";
			} else {
				// check for special charactor check, expect ,&-
				Matcher hasSpecial = special.matcher(fieldValue);
				boolean b = hasSpecial.find();
				if (b) {
					fieldValue = "";
				} else {
					// apply length validation
					String specialChars = "/*!@#$%^&*()\"{}_[]|\\?/<>,.";
					fieldValue = applyRulesForLengthofField(fieldValue, length);
					// remove all last special charactor from fieldvalue is
					// exist
					char[] ch = fieldValue.toCharArray();
					for (int i = ch.length - 1; i >= 0; i--) {
						char lastChar = fieldValue.charAt(fieldValue.length() - 1);
						if (specialChars.contains(String.valueOf(lastChar))) {
							fieldValue = fieldValue.substring(0, fieldValue.length() - 1);
						} else {
							break;
						}
					}
				}
			}

		}
		realMap.put("Field_59", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_60(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap, String fieldsNumbers) {
		String positionValue = "";
		Pattern special = Pattern.compile("[!@#$%*()_+=|<>?{}\\[\\]~-]");
		String[] arr = fieldsNumbers.split(",");
		List<String> arrPosition = Arrays.asList(arr);
		if (arrPosition.contains("57")) {
			int fieldPosVal = arrPosition.indexOf("57");
			fieldPosVal = fieldPosVal + 1;
			if (singleRowData[fieldPosVal] != null)
				positionValue = singleRowData[fieldPosVal].toString();
			if (positionValue == null || positionValue.isEmpty()) {
				fieldValue = "";
			} else {
				// check for special charactor check, expect ,&-
				Matcher hasSpecial = special.matcher(fieldValue);
				boolean b = hasSpecial.find();
				if (b) {
					fieldValue = "";
				} else {
					// apply length validation
					String specialChars = "/*!@#$%^&*()\"{}_[]|\\?/<>,.";
					fieldValue = applyRulesForLengthofField(fieldValue, length);
					// remove all last special charactor from fieldvalue is
					// exist
					char[] ch = fieldValue.toCharArray();
					for (int i = ch.length - 1; i >= 0; i--) {
						char lastChar = fieldValue.charAt(fieldValue.length() - 1);
						if (specialChars.contains(String.valueOf(lastChar))) {
							fieldValue = fieldValue.substring(0, fieldValue.length() - 1);
						} else {
							break;
						}
					}
				}
			}

		}
		realMap.put("Field_60", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_61(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap, String fieldsNumbers) {
		String positionValue = "";
		Pattern special = Pattern.compile("[!@#$%*()_+=|<>?{}\\[\\]~-]");
		String[] arr = fieldsNumbers.split(",");
		List<String> arrPosition = Arrays.asList(arr);
		if (arrPosition.contains("57")) {
			int fieldPosVal = arrPosition.indexOf("57");
			fieldPosVal = fieldPosVal + 1;
			if (singleRowData[fieldPosVal] != null)
				positionValue = singleRowData[fieldPosVal].toString();
			if (positionValue == null || positionValue.isEmpty()) {
				fieldValue = "";
			} else {
				// check for special charactor check, expect ,&-
				Matcher hasSpecial = special.matcher(fieldValue);
				boolean b = hasSpecial.find();
				if (b) {
					fieldValue = "";
				} else {
					// apply length validation
					String specialChars = "/*!@#$%^&*()\"{}_[]|\\?/<>,.";
					fieldValue = applyRulesForLengthofField(fieldValue, length);
					// remove all last special charactor from fieldvalue is
					// exist
					char[] ch = fieldValue.toCharArray();
					for (int i = ch.length - 1; i >= 0; i--) {
						char lastChar = fieldValue.charAt(fieldValue.length() - 1);
						if (specialChars.contains(String.valueOf(lastChar))) {
							fieldValue = fieldValue.substring(0, fieldValue.length() - 1);
						} else {
							break;
						}
					}
				}
			}

		}
		realMap.put("Field_61", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_62(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap, String fieldsNumbers) {
		String positionValue = "";
		String[] arr = fieldsNumbers.split(",");
		List<String> arrPosition = Arrays.asList(arr);
		if (arrPosition.contains("57")) {
			int fieldPosVal = arrPosition.indexOf("57");
			fieldPosVal = fieldPosVal + 1;
			if (singleRowData[fieldPosVal] != null)
				positionValue = singleRowData[fieldPosVal].toString();
			if (positionValue == null || positionValue.isEmpty()) {
				fieldValue = "";
			} else {
				// apply length validation
				fieldValue = applyRulesForLengthofField(fieldValue, length);
				// remove all last special charactor from fieldvalue is exist
			}
		}
		realMap.put("Field_62", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_63(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap, String fieldsNumbers) {
		Pattern special = Pattern.compile("[!@#$%*()_+=|<>?{}\\[\\]~-]");
		String[] arr = fieldsNumbers.split(",");
		List<String> arrPosition = Arrays.asList(arr);
		String positionValue = "";
		if (arrPosition.contains("57")) {
			int fieldPosVal = arrPosition.indexOf("57");
			fieldPosVal = fieldPosVal + 1;
			if (singleRowData[fieldPosVal] != null)
				positionValue = singleRowData[fieldPosVal].toString();
			if (positionValue == null || positionValue.isEmpty()) {
				fieldValue = "";
			} else {
				// check for special charactor check, expect ,&-
				Matcher hasSpecial = special.matcher(fieldValue);
				boolean b = hasSpecial.find();
				if (b) {
					fieldValue = "";
				} else {
					// apply length validation
					String specialChars = "/*!@#$%^&*()\"{}_[]|\\?/<>,.";
					fieldValue = applyRulesForLengthofField(fieldValue, length);
					// remove all last special charactor from fieldvalue is
					// exist
					char[] ch = fieldValue.toCharArray();
					for (int i = ch.length - 1; i >= 0; i--) {
						char lastChar = fieldValue.charAt(fieldValue.length() - 1);
						if (specialChars.contains(String.valueOf(lastChar))) {
							fieldValue = fieldValue.substring(0, fieldValue.length() - 1);
						} else {
							break;
						}
					}
				}
			}

		}
		realMap.put("Field_63", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_64(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap, String fieldsNumbers) {
		Pattern p = Pattern.compile("[^A-Za-z0-9]");
		// Matcher m = p.matcher(s);
		String positionValue = "";
		String[] arr = fieldsNumbers.split(",");
		List<String> arrPosition = Arrays.asList(arr);
		if (arrPosition.contains("57")) {
			int fieldPosVal = arrPosition.indexOf("57");
			fieldPosVal = fieldPosVal + 1;
			if (singleRowData[fieldPosVal] != null)
				positionValue = singleRowData[fieldPosVal].toString();
			if (positionValue == null || positionValue.isEmpty()) {
				fieldValue = "";
			} else {
				// check for special charactor check, expect ,&-

				// apply length validation
				String fieldValue1 = "";

				for (int i = 0; i < fieldValue.length(); i++)

				{

					// Ascci range for a-z A-Z

					if (fieldValue.charAt(i) > 64 && fieldValue.charAt(i) < 121)

					{

						fieldValue1 += fieldValue.charAt(i);

					}

				}
				fieldValue = applyRulesForLengthofField(fieldValue1, length);
				// remove all last special charactor from fieldvalue is
				// exist
			}

		}
		realMap.put("Field_64", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_65(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap, String fieldsNumbers) {
		Pattern p = Pattern.compile("[^A-Za-z0-9]");
		// Matcher m = p.matcher(s);
		String positionValue = "";
		String[] arr = fieldsNumbers.split(",");
		List<String> arrPosition = Arrays.asList(arr);
		if (arrPosition.contains("57")) {
			int fieldPosVal = arrPosition.indexOf("57");
			fieldPosVal = fieldPosVal + 1;
			if (singleRowData[fieldPosVal] != null)
				positionValue = singleRowData[fieldPosVal].toString();
			if (positionValue == null || positionValue.isEmpty()) {
				fieldValue = "";
			} else {
				// check for special charactor check, expect ,&-

				// apply length validation
				String fieldValue1 = "";

				for (int i = 0; i < fieldValue.length(); i++)

				{
					if (fieldValue.charAt(i) > 64 && fieldValue.charAt(i) < 121) {
						fieldValue1 += fieldValue.charAt(i);
					}
				}
				fieldValue = applyRulesForLengthofField(fieldValue1, length);
			}
		}
		realMap.put("Field_65", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_66(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap, String fieldsNumbers) {
		Pattern special = Pattern.compile("[!@#$%*()_+=|<>?{}\\[\\]~-]");
		String[] arr = fieldsNumbers.split(",");
		String positionValue = "";
		List<String> arrPosition = Arrays.asList(arr);
		if (arrPosition.contains("57")) {
			int fieldPosVal = arrPosition.indexOf("57");
			fieldPosVal = fieldPosVal + 1;
			if (singleRowData[fieldPosVal] != null)
				positionValue = singleRowData[fieldPosVal].toString();
			if (positionValue == null || positionValue.isEmpty()) {
				fieldValue = "";
			} else {
				// check for special charactor check, expect ,&-
				Matcher hasSpecial = special.matcher(fieldValue);
				boolean b = hasSpecial.find();
				if (b) {
					fieldValue = "";
				} else {
					// apply length validation
					String specialChars = "/*!@#$%^&*()\"{}_[]|\\?/<>,.";
					fieldValue = applyRulesForLengthofField(fieldValue, length);
					// remove all last special charactor from fieldvalue is
					// exist
					char[] ch = fieldValue.toCharArray();
					for (int i = ch.length - 1; i >= 0; i--) {
						char lastChar = fieldValue.charAt(fieldValue.length() - 1);
						if (specialChars.contains(String.valueOf(lastChar))) {
							fieldValue = fieldValue.substring(0, fieldValue.length() - 1);
						} else {
							break;
						}
					}
				}
			}

		}
		realMap.put("Field_66", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_67(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap, String fieldsNumbers) {
		Pattern special = Pattern.compile("[!@#$%*()_+=|<>?{}\\[\\]~-]");
		String[] arr = fieldsNumbers.split(",");
		String positionValue = "";
		List<String> arrPosition = Arrays.asList(arr);
		if (arrPosition.contains("57")) {
			int fieldPosVal = arrPosition.indexOf("57");
			fieldPosVal = fieldPosVal + 1;
			if (singleRowData[fieldPosVal] != null)
				positionValue = singleRowData[fieldPosVal].toString();
			if (positionValue == null || positionValue.isEmpty()) {
				fieldValue = "";
			} else {
				// check for special charactor check, expect ,&-
				Matcher hasSpecial = special.matcher(fieldValue);
				boolean b = hasSpecial.find();
				if (b) {
					fieldValue = "";
				} else {
					// apply length validation
					String specialChars = "/*!@#$%^&*()\"{}_[]|\\?/<>,.";
					fieldValue = applyRulesForLengthofField(fieldValue, length);
					// remove all last special charactor from fieldvalue is
					// exist
					char[] ch = fieldValue.toCharArray();
					for (int i = ch.length - 1; i >= 0; i--) {
						char lastChar = fieldValue.charAt(fieldValue.length() - 1);
						if (specialChars.contains(String.valueOf(lastChar))) {
							fieldValue = fieldValue.substring(0, fieldValue.length() - 1);
						} else {
							break;
						}
					}
				}
			}
			realMap.put("Field_67", fieldValue);
		}
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_68(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_68", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_69(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_69", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_70(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_70", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_71(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_71", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_72(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_72", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_73(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_73", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_74(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_74", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_75(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_75", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_76(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap, String fieldsNumbers) {
		// Check 76 field value is exist or not. If exist the do validation but
		// if not exist then get value from 69 field.
		if (fieldValue != null || !fieldValue.isEmpty()) {
			fieldValue = applyRuleFor_76Field(fieldValue);
         //apply rule for table data 
		//	fieldValue = applyRuleFor_76Field(fieldValue);
		} else if (fieldValue == null || fieldValue.isEmpty()) {
			String[] arr = fieldsNumbers.split(",");
			List<String> arrPosition = Arrays.asList(arr);
			if (arrPosition.contains("69")) {
				int fieldPosVal = arrPosition.indexOf("69");
				fieldPosVal = fieldPosVal + 1;
				if (singleRowData[fieldPosVal] != null)
					fieldValue = singleRowData[fieldPosVal].toString();
			}

			fieldValue = replaceMoreThanOneSpaceWithSingleSpace(fieldValue);
			String[] split = fieldValue.split(" ");
			String beforeAC = "";
			String afterAC = "";
			int acFount = 0;
			for (int i = 0; i < split.length; i++) {
				if (split[i].toString().equals("AC") || split[i].contains("ACARES") || split[i].contains("ACRE")) {
					// LTCount = LTCount + 1;
					// field_76_Type = split[i].toString();
					acFount = acFount + 1;
				} else {
					if (acFount == 1) {
						afterAC = split[i].toString();
						// acFount = 0;
						// break;
					} else {
						beforeAC = split[i].toString();
					}
					if (acFount == 1) {
						if (isNumeric(beforeAC) || isNumeric(afterAC)) {
							break;
						}
						acFount = 0;
					}
				}
				if (isNumeric(beforeAC)) {
					fieldValue = applyRuleFor_76Field(beforeAC);
					// Go with beforeAc
				} else if (isNumeric(afterAC)) {
					fieldValue = applyRuleFor_76Field(afterAC);
					// Go with afterAC
				} else {
					fieldValue = "";
				}
			}
		}
		// fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_76", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_77(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_77", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_78(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_78", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_79(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_79", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_80(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_80", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_81(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_81", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_82(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_82", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_83(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_83", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_84(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_84", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_85(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_85", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_86(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_86", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_87(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_87", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_88(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_88", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_89(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_89", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_90(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		try {
			boolean alphaNumericCheck = isAlphaNumeric(fieldValue);
			boolean numericCheck = isNumeric(fieldValue);
			if (numericCheck) {
				throw new Exception("Field_90 value =" + fieldValue + "is Alpha Numberic.");
			}
			if (alphaNumericCheck) {
				fieldValue = applyRulesForLengthofField(fieldValue, length);
			} else {
				fieldValue = applyRulesForLengthofField(fieldValue, length);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_90", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_91(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		try {
			boolean numericCheck = isNumeric(fieldValue);
			if (numericCheck) {
				if (fieldValue.length() == 6) {
					String vmonth = fieldValue.substring(0, 2);
					String vyear = fieldValue.substring(2, fieldValue.length());
					Date date = new Date();
					Calendar cal = Calendar.getInstance();
					cal.setTime(date);
					int year = cal.get(Calendar.YEAR);
					int month = cal.get(Calendar.MONTH);
					int day = cal.get(Calendar.DAY_OF_MONTH);
					if (Integer.parseInt(vmonth) >= 1 && Integer.parseInt(vmonth) <= 12) {
					} else {
						throw new Exception("Field 91 is not valid Month=" + vmonth);
					}

					if (Integer.parseInt(vyear) >= 1637 && Integer.parseInt(vyear) <= year) {
					} else {
						throw new Exception("Field 91 is not valid year=" + vyear);
					}
				} else {
					throw new Exception("Field 91 have more than 6 digits=" + fieldValue);
				}
				// fieldValue = applyRulesForLengthofField(fieldValue, length);
			} else {
				throw new Exception("Field 091 is not numeric=" + fieldValue);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_91", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_92(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_92", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_93(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_93", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_94(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		if (fieldValue == "" || fieldValue == null) {
			fieldValue = "0000000000";
		}
		boolean b = isNumeric(fieldValue);
		if (b) {
			fieldValue = applyRulesForLengthofField(fieldValue, length);
		}
		// fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_94", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_95(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		if (fieldValue == "" || fieldValue == null) {
			fieldValue = "0000000000";
		}
		boolean b = isNumeric(fieldValue);
		if (b) {
			fieldValue = applyRulesForLengthofField(fieldValue, length);
		}
		// fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_95", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_96(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap, String fieldsNumbers) {
		try {
			int fieldPosVal = 0;
			String positionValue = "";
			String[] arr = fieldsNumbers.split(",");
			List<String> arrPosition = Arrays.asList(arr);
			if (fieldValue == null || fieldValue.isEmpty()) {
				fieldValue = "00000000000";
			}
			if (arrPosition.contains("94") && arrPosition.contains("95")) {
				if (field_94_value > 0 || field_95_value > 0) {
					int sum = field_94_value + field_95_value;
					fieldValue = String.valueOf(sum);
				}
			} else {
				fieldValue = "00000000000";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_96", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_97(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_97", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_98(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_98", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_99(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_99", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_100(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_100", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_101(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_101", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_102(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_102", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_103(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_103", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_104(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_104", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_105(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_105", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_106(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_106", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_107(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_107", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_108(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_108", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_109(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		try {
			boolean b = isNumeric(fieldValue);
			if (b) {
				if (fieldValue.length() >= 2) {
					fieldValue = applyRulesForLengthofField(fieldValue, length);
				} else if (fieldValue.length() == 1) {
					fieldValue = "0" + fieldValue;
				}
			} else {
				throw new Exception("Field 109 is not numeric " + fieldValue);
			}
		} catch (Exception e) {
			e.printStackTrace();
			// throw new Exception("Exception for field 109 "+e);
		}
		// fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_109", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_110(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_110", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_111(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_111", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_112(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_112", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_113(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_113", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_114(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_114", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_115(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_115", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_116(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_116", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_117(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_117", fieldValue);
		return realMap;
		// return realMap;;
	}

	public static Map<String, String> forField_118(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_118", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_119(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_119", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_120(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_120", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_121(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_121", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_122(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_122", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_123(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_123", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_124(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap, String fieldsNumbers) {
		int fieldPosVal = 0;
		String positionValue = "";
		boolean check = isAlphaNumeric(fieldValue);
		if (check) {
			fieldValue = "";
		} else {
			
			String field_18 = realMap.get("Field_18");
			if(field_18 != null || !field_18.isEmpty())
			{
				if (field_18.equalsIgnoreCase("Not Available from the County")) {
					fieldValue = "";
				}
			}else
			{
				fieldValue = "";	
			}
			/*String[] arr = fieldsNumbers.split(",");
			List<String> arrPosition = Arrays.asList(arr);
			if (arrPosition.contains("18")) {
				fieldPosVal = arrPosition.indexOf("18");
				fieldPosVal = fieldPosVal + 1;
				if (singleRowData[fieldPosVal] != null)
					positionValue = singleRowData[fieldPosVal].toString();
				if (positionValue == null || positionValue.isEmpty()) {
					fieldValue = "";
				} else if (positionValue.equalsIgnoreCase("Not Available from the County")) {
					fieldValue = "";
				}
			} else {
				fieldValue = "";
			}*/
			fieldValue = applyRulesForLengthofField(fieldValue, length);
		}
		realMap.put("Field_124", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_125(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		int fieldPosVal = 0;
		String positionValue = "";
		boolean check = isAlphaNumeric(fieldValue);
		if (check) {
			fieldValue = "";
		} else {
			
			String field_19 = realMap.get("Field_19");
			if(field_19 != null || !field_19.isEmpty())
			{
				/*if (field_18.equalsIgnoreCase("Not Available from the County")) {
					fieldValue = "";
				}*/
			}else
			{
				fieldValue = "";	
			}
			/*String[] arr = fieldsNumbers.split(",");
			List<String> arrPosition = Arrays.asList(arr);
			if (arrPosition.contains("18")) {
				fieldPosVal = arrPosition.indexOf("18");
				fieldPosVal = fieldPosVal + 1;
				if (singleRowData[fieldPosVal] != null)
					positionValue = singleRowData[fieldPosVal].toString();
				if (positionValue == null || positionValue.isEmpty()) {
					fieldValue = "";
				} else if (positionValue.equalsIgnoreCase("Not Available from the County")) {
					fieldValue = "";
				}
			} else {
				fieldValue = "";
			}*/
			fieldValue = applyRulesForLengthofField(fieldValue, length);
		}
		//fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_125", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_126(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		int fieldPosVal = 0;
		String positionValue = "";
		boolean check = isAlphaNumeric(fieldValue);
		if (check) {
			fieldValue = "";
		} else {
			
			String field_22 = realMap.get("Field_22");
			if(field_22 != null || !field_22.isEmpty())
			{
/*				if (field_18.equalsIgnoreCase("Not Available from the County")) {
					fieldValue = "";
				}*/
			}else
			{
				fieldValue = "";	
			}
			/*String[] arr = fieldsNumbers.split(",");
			List<String> arrPosition = Arrays.asList(arr);
			if (arrPosition.contains("18")) {
				fieldPosVal = arrPosition.indexOf("18");
				fieldPosVal = fieldPosVal + 1;
				if (singleRowData[fieldPosVal] != null)
					positionValue = singleRowData[fieldPosVal].toString();
				if (positionValue == null || positionValue.isEmpty()) {
					fieldValue = "";
				} else if (positionValue.equalsIgnoreCase("Not Available from the County")) {
					fieldValue = "";
				}
			} else {
				fieldValue = "";
			}*/
			fieldValue = applyRulesForLengthofField(fieldValue, length);
		}
		//fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_126", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_127(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_127", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_128(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_128", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_129(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_129", fieldValue);
		return realMap;
		// return realMap;
	}

	public static Map<String, String> forField_130(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		// done
		try {
			boolean numericCheck = isNumeric(fieldValue);
			if (numericCheck) {
				if (fieldValue.length() == 8) {
					boolean dateCheck = isDateValid(fieldValue);
					if (!dateCheck) {
						fieldValue = "";
						throw new Exception("Field 130 dont have valid date");
					}
				}
				// fieldValue = applyRulesForLengthofField(fieldValue, length);
			} else {
				fieldValue = "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_130", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_131(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		String field76 = realMap.get("Field_76");
		if (field76 != null || !field76.isEmpty()) {
			fieldValue = realMap.get("Field_76");
			if(fieldValue.contains(" X "))
			{
				if(fieldValue.contains("FF") || fieldValue.contains("FT"))
				{
					fieldValue = "";
				}else{
					String[] dimSplit = fieldValue.split(" X ");
			        int fnlResult = Integer.parseInt(dimSplit[0].trim())*Integer.parseInt(dimSplit[1].trim());
			        if(String.valueOf(fnlResult).length() < 9)
			        {
			        	fieldValue = String.valueOf(fnlResult);
			        	for(int i=0;i<9;i++)
			        	{
			        		fieldValue = "0"+fieldValue;
			        		if(fieldValue.length()==9)
			        		{
			        			break;
			        		}
			        	}
			        }else if(String.valueOf(fnlResult).length() >9)
			        {
			        	fieldValue = "";
			        }
				}				
			}else
			{
				boolean alphaNumChk = isAlphaNumeric(fieldValue);
				if (alphaNumChk) {
					fieldValue = removeAllAlphaChartersFromAlphaNumString(fieldValue);
					double val = Double.parseDouble(fieldValue) * 43560;
					fieldValue = String.valueOf(val);
					if (fieldValue.length() > 9) {
						fieldValue = "";
					}
				}
			}
		} else {
			fieldValue = "";
		}
		// fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_131", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_132(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_132", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_133(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_133", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_134(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_134", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_135(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_135", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_136(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_136", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_137(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_137", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_138(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_138", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_139(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_139", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_140(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_140", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_141(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_141", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_142(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_142", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_143(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_143", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_144(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_144", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_145(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_145", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_146(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_146", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_147(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_147", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_148(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_148", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_149(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_149", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_150(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_150", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_151(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		// Need to ask that field 151 value will be table data or taken from 76
		// field.
		
		 fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_151", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_152(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		String field76 = realMap.get("Field_76");
		if (field76 != null || !field76.isEmpty()) {
			fieldValue = realMap.get("Field_76");
			if(fieldValue.contains(" X "))
			{
				if(fieldValue.contains("FF") || fieldValue.contains("FT"))
				{
					fieldValue = "";
				}else{
					String[] dimSplit = fieldValue.split(" X ");
			        int fnlResult = Integer.parseInt(dimSplit[0].trim())*Integer.parseInt(dimSplit[1].trim());
			        fnlResult = fnlResult/43560;
			        if(String.valueOf(fnlResult).length() < 10)
			        {
			        	fieldValue = String.valueOf(fnlResult);
			        	for(int i=0;i<9;i++)
			        	{
			        		fieldValue = "0"+fieldValue;
			        		if(fieldValue.length()==9)
			        		{
			        			break;
			        		}
			        	}
			        }else if(String.valueOf(fnlResult).length() >10)
			        {
			        	fieldValue = "";
			        }
				}				
			}else
			{
				if (field76 != null || !field76.isEmpty()) {
					fieldValue = removeAllSpecialCharacters(fieldValue);
					fieldValue = removeAllAlphaChartersFromAlphaNumString(fieldValue);
					boolean doubleCheck = isInteger(Double.parseDouble(fieldValue));
					if (!doubleCheck) {
						String afterDecimal = fieldValue.substring(fieldValue.indexOf(".") + 1, fieldValue.length());
						String beforeDecimal = fieldValue.substring(0, fieldValue.indexOf("."));
						if (afterDecimal.length() == 1) {
							fieldValue = String.valueOf(Double.parseDouble(fieldValue) * 1000);
						} else if (afterDecimal.length() == 2) {
							fieldValue = String.valueOf(Double.parseDouble(fieldValue) * 1000);
						} else if (afterDecimal.length() == 3) {
							fieldValue = String.valueOf(Double.parseDouble(fieldValue) * 1000);
						}
						fieldValue = fieldValue.substring(0, fieldValue.indexOf("."));

					} else if (doubleCheck) {
						if (fieldValue.length() == 1) {
							fieldValue = String.valueOf(Integer.parseInt(fieldValue) * 1000);
						} else if (fieldValue.length() == 2) {
							fieldValue = String.valueOf(Integer.parseInt(fieldValue) * 100);
						} else if (fieldValue.length() == 3) {
							fieldValue = String.valueOf(Integer.parseInt(fieldValue) * 10);
						}
					}
					if (fieldValue.length() < 10) {
						for (int i = 0; i < 10; i++) {
							fieldValue = "0" + fieldValue;
							if (fieldValue.length() == 10)
								break;
						}
						// fieldValue = addZeroBeforeTheString(fieldValue);
					}

				} else {
					fieldValue = "";
				}
			}
		} else {
			fieldValue = "";
		}
	//	fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_152", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_153(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_153", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_154(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_154", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_155(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_155", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_156(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_156", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_157(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_157", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_158(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_158", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_159(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_159", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_160(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_160", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_161(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_161", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_162(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_162", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_163(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_163", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_164(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_163", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_165(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_165", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_166(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_166", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_167(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_167", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_168(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_168", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_169(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_169", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_170(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_170", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_171(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_171", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_172(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_172", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_173(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_173", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_174(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_174", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_175(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_175", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_176(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_176", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_177(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_177", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_178(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_178", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_179(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_179", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_180(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_180", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_181(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_181", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_182(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_182", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_183(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_183", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_184(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_184", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_185(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_185", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_186(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_186", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_187(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_187", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_188(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_188", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_189(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_189", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_190(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_190", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_191(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_191", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_192(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_192", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_193(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_193", fieldValue);
		return realMap;
	}

	public static Map<String, String> forField_194(String fieldValue, int length, Object[] singleRowData,
			List<Object[]> list, Map<String, String> realMap) {
		fieldValue = applyRulesForLengthofField(fieldValue, length);
		realMap.put("Field_194", fieldValue);
		return realMap;
	}

	public static String applyRulesForLengthofField(String mappedValue, int length) {
		log4j.error("ApplyingFieldRules - applyRulesForLengthofField :: Start ");
		try {
			if (mappedValue != null) {
				if (!mappedValue.equals("")) {
					Matcher firstCharMatch = pattern.matcher(Character.toString(mappedValue.charAt(0)));
					Matcher secondCharMatch = pattern
							.matcher(Character.toString(mappedValue.charAt(mappedValue.length() - 1)));
					if (firstCharMatch.find()) {
						mappedValue = mappedValue.substring(1);
					}
					if (secondCharMatch.find()) {
						mappedValue = mappedValue.substring(0, mappedValue.length() - 2);
					}
				}

				if (mappedValue.length() > length) {
					mappedValue = mappedValue.substring(0, length);
				}
				/*
				 * if (mappedValue.length() < length) { int len = length -
				 * mappedValue.length(); for (int k = 1; k <= len; k++) {
				 * mappedValue = mappedValue.concat(" "); } } else if
				 * (mappedValue.length() > length) { mappedValue =
				 * mappedValue.substring(0, length); }
				 */
			}
		} catch (Exception e) {
			log4j.error("ApplyingFieldRules - applyRulesForLengthofField :: Error " + e.getMessage());
			e.printStackTrace();
		}
		log4j.error("ApplyingFieldRules - applyRulesForLengthofField :: End ");
		return mappedValue;
	}

	public static boolean isAlphaNumeric(String s) {
		// System.out.println("Matches = "+s.matches(".*\\d.*"));
		boolean flag = false;
		if (s != null) {
			flag = s.matches(".*\\d.*");
		}
		return flag;
	}

	public static boolean isNumeric(String str) {
		try {
			double d = Double.parseDouble(str);
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}

	public static boolean isDateValid(String date) {
		final String DATE_FORMAT = "dd-MM-yyyy";
		try {
			if (date != null) {
				DateFormat df = new SimpleDateFormat(DATE_FORMAT);
				df.setLenient(false);
				df.parse(date);
				return true;
			} else {
				return false;
			}
		} catch (ParseException e) {
			return false;
		}
	}

	public static String removeSpecialCharactersExpectHyphen(String value) {
		return value.replaceAll("[^\\w-]", "");
	}

	public static String removeAllSpecialCharacters(String value) {
		return value.replaceAll("[^\\w]", "");
	}

	public static boolean validateDateMonthYear(int day, int month, int year, int yearFrom, int yearTo) {
		boolean val = false;
		if (year >= yearFrom && year < yearTo) // test of the year
		{
			if (month >= 1 && month <= 12) {
				val = true;
			}
			if ((month == 1) && (day >= 1 && day <= 31)) {
				val = true;
			} else {
				val = false;
			}
			if ((month == 2) && (day >= 1 && day <= 29)) {
				val = true;
			} else {
				val = false;
			}
			if ((month == 3) && (day >= 1 && day <= 31)) {
				val = true;
			} else {
				val = false;
			}
			if ((month == 4) && (day >= 1 && day <= 30)) {
				val = true;
			} else {
				val = false;
			}
			if ((month == 5) && (day >= 1 && day <= 31)) {
				val = true;
			} else {
				val = false;
			}
			if ((month == 6) && (day >= 1 && day <= 30)) {
				val = true;
			} else {
				val = false;
			}
			if ((month == 7) && (day >= 1 && day <= 31)) {
				val = true;
			} else {
				val = false;
			}
			if ((month == 8) && (day >= 1 && day <= 31)) {
				val = true;
			} else {
				val = false;
			}
			if ((month == 9) && (day >= 1 && day <= 30)) {
				val = true;
			} else {
				val = false;
			}
			if ((month == 10) && (day >= 1 && day <= 31)) {
				val = true;
			} else {
				val = false;
			}
			if ((month == 11) && (day >= 1 && day <= 30)) {
				val = true;
			} else {
				val = false;
			}
			if ((month == 12) && (day >= 1 && day <= 31)) {
				return true;
			} else {
				val = false;
			}
		} else {
			val = false;
		}
		return val;
	}

	public static boolean isAlpha(String name) {
		return name.matches("[a-zA-Z]+");
	}

	public static boolean isInteger(double number) {
		return Math.ceil(number) == Math.floor(number);
	}

	public static String applyRuleFor_76Field(String fieldValue) {
		boolean numCheck = isNumeric(fieldValue);
		if (numCheck) {
			int value = 0;
			boolean checkDouble = isInteger(Double.parseDouble(fieldValue));
			if (!checkDouble) {
				double d = Double.parseDouble(fieldValue);
				value = (int) d;
				// fieldValue = String.valueOf(a);
				if (value < 1) {
					double fieldVal = Double.parseDouble(fieldValue) * 46560;
					long tmp = Math.round(fieldVal);
					fieldValue = fieldValue + " SF";
				} else {
					boolean b1 = isInteger(Double.parseDouble(fieldValue));
					if (b1) {
						fieldValue = fieldValue + ".000 AC";
					} else {
						String[] split = fieldValue.split("\\.");
						if (split[1].length() > 3) {
							if (split[1].length() > 4) {
								split[1] = split[1].substring(0, 4);
							}
							String lastDigit = split[1].substring(split[1].length() - 1, split[1].length());
							String secondLastDigit = split[1].substring(split[1].length() - 2, split[1].length() - 1);
							int lDigit = Integer.parseInt(lastDigit);
							int slDigit = Integer.parseInt(secondLastDigit);
							if (lDigit > slDigit) {
								fieldValue = fieldValue.substring(0, fieldValue.length() - 2) + lastDigit;
								// replace the digit
							}
						} else {
							if (split[1].length() == 1) {
								fieldValue = fieldValue + "00 AC";
							} else if (split[1].length() == 2) {
								fieldValue = fieldValue + "0 AC";
							}
						}
					}
				}
			} else if (Integer.parseInt(fieldValue) >= 1) {
				boolean b1 = isInteger(Double.parseDouble(fieldValue));
				if (b1) {
					fieldValue = fieldValue + ".000 AC";
				} else {
					String[] split = fieldValue.split("\\.");
					if (split[1].length() > 3) {
						if (split[1].length() > 4) {
							split[1] = split[1].substring(0, 4);
						}
						String lastDigit = split[1].substring(split[1].length() - 1, split[1].length());
						String secondLastDigit = split[1].substring(split[1].length() - 2, split[1].length() - 1);
						int lDigit = Integer.parseInt(lastDigit);
						int slDigit = Integer.parseInt(secondLastDigit);
						if (lDigit > slDigit) {
							fieldValue = fieldValue.substring(0, fieldValue.length() - 2) + lastDigit;
							// replace the digit
						}
					} else {
						if (split[1].length() == 1) {
							fieldValue = fieldValue + "00 AC";
						} else if (split[1].length() == 2) {
							fieldValue = fieldValue + "0 AC";
						}
					}
				}
			}
		} else {
			fieldValue = "";
		}
		return fieldValue;
	}

	public static String removeAllAlphaChartersFromAlphaNumString(String fieldValue) {
		return fieldValue.replaceAll("[^\\d.]", "");
	}
	public static String replaceMoreThanOneSpaceWithSingleSpace(String fieldValue) {
		return fieldValue.replaceAll("\\s{2,}", " ").trim();
	}
}
