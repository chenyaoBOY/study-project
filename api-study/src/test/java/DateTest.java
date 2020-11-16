import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * @author chenyao
 * @date 2019/11/4 17:46
 * @description
 */
public class DateTest {


    @Test
    public void test(){

        Calendar calendar = Calendar.getInstance();

        calendar.add(Calendar.DAY_OF_YEAR,-333);
        System.out.println(calendar.getTime());

    }
    @Test
    public void test2(){
        String s="{\"code\":0,\"msg\":\"成功\",\"data\":{\"userDetail\":{\"userId\":1000003932,\"userEmail\":null,\"emailValid\":0,\"mobileValid\":1,\"userSource\":12,\"nickName\":\"sf149204959981866\",\"userMobile\":\"13241722446\",\"realName\":\"\",\"userSex\":\"0\",\"province\":2,\"userName\":\"sf149204959981866\",\"birthday\":0,\"degree\":0,\"income\":0,\"cards\":\"\",\"userState\":1,\"mobileValidTime\":null,\"emailValidTime\":null,\"addOrFind\":null,\"channelName\":null,\"primary\":null,\"userVersion\":0,\"memNo\":\"\",\"owner\":null,\"channel\":null,\"userOrgId\":\"B755P\",\"userMdId\":\"755TBS\",\"regOrgId\":\"\",\"regMdId\":\"\",\"encryptUId\":\"mL1IP1vwg3Oo7OpSCPMOxQ==\",\"scopeFromId\":null,\"from\":15,\"partnerId\":\"0\",\"userPwd\":\"e10adc3949ba59abbe56e057f20f883e\",\"regIp\":null,\"rankChangeTime\":null,\"userType\":\"2\",\"userRank\":2,\"userBalance\":8888,\"forzenMoney\":0,\"payPoints\":9403606,\"payPointsT1\":614,\"forzenPoint\":82,\"loginCount\":0,\"country\":1,\"city\":0,\"district\":0,\"fundTotal\":0,\"fundLastDonation\":0,\"fundAllDonation\":0,\"fundBalance\":0,\"dmCode\":null,\"cpaParams\":null,\"regTime\":1492049599,\"smsCode\":null,\"isBlack\":0,\"payPwd\":null,\"pwdLevel\":0,\"devNo\":null,\"paypwdState\":null,\"userTelpwd\":null,\"userTel\":null,\"rankPoints\":null,\"regType\":null,\"relateMobile\":null,\"isPerfectData\":0,\"consumePwd\":null,\"consumePwdAddTime\":null,\"consumePwdState\":0,\"invitationCode\":null,\"giftCardBalance\":0,\"memberType\":20,\"memberStatus\":1,\"expiredTime\":1915004178,\"memberSaveMoney\":420,\"cardNumber\":\"\",\"cardBalance\":0,\"memberDay\":null,\"memberStartTime\":null,\"memberRecordType\":null,\"importSn\":null,\"userMdFrom\":null,\"picture\":null,\"sendCoupon\":null,\"sendType\":null},\"channelNo\":null}}";
        JSONObject object = JSONObject.parseObject(s);
        JSONObject data = object.getJSONObject("data").getJSONObject("userDetail");
        System.out.println(data.getString("userName"));
    }
}
