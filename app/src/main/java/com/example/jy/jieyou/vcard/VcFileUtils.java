package com.example.jy.jieyou.vcard;

import net.sourceforge.cardme.vcard.VCard;
import net.sourceforge.cardme.vcard.exceptions.VCardParseException;
import net.sourceforge.cardme.vcard.types.TelType;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by jhf on 2019/10/25.
 */

public class VcFileUtils {

    public static String getVcFile(String filePath) {
        File file = new File(filePath);
        VCardEngine vce = new VCardEngine();//new一个vcard引擎
        String mString = "";
        try {
            /*
	    	 * 一个vcf文件中的联系人不止有一个，我们使用parseMultiple来
	    	 * 操作文件，如果只有一个的话 ，使用parse(file)即可。
	    	 * 一个联系人对应一个VCard对象；我们读的vcf文件有多个联系人，因此
	    	 * 用List来存储。
	    	 *
	    	 * */
            List<VCard> vcards = vce.parseMultiple(file);
            for (VCard vCard : vcards) {
                //为什么用list存储每个人的联系电话，因为我们知道通讯录里
                //可以记录多个联系电话。
                List<TelType> teltype = vCard.getTels();
				/*某些用户就是不让人省心，只写联系人姓名，不填电话号码
				 * 真是的，我们只能来个判断了。
				 */
                if (teltype != null && teltype.size() != 0) {
                    for (TelType telType2 : teltype) {
                        //循环打印出该联系人的联系电话;
                        mString = mString  + telType2.getTelephone() + ",";
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (VCardParseException e) {
            e.printStackTrace();
        }
        return mString;
    }
}
