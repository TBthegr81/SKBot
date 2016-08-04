package botMk3;

import java.util.ArrayList;

/**
 * Created by TB on 2016-08-03.
 */
public class Test {
    ArrayList<String> answers;

    public Test() {
        answers = new ArrayList<String>();

        ArrayList<String> input = new ArrayList<String>();
        input.add("http://www.webhallen.com/se-sv/spel/pc/208881-overwatch");
        input.add("https://www.amazon.co.uk/Deadpool-Blu-ray-Ryan-Reynolds/dp/B01BDUS0NY/ref=sr_1_1?s=dvd&ie=UTF8&qid=1470253968&sr=1-1&keywords=deadpool");
        input.add("https://www.mathem.se/varor/herrgardsost/herrgardsost-28--ca-667g-arla");
        input.add("https://www.inet.se/produkt/1999749/samsung-galaxy-s7-edge-svart/#merinfo");
        input.add("http://shop.lego.com/en-SE/Volkswagen-Beetle-10252?icmp=SHHomeMain1_EU_Beetle");
        input.add("http://www.ikea.com/se/sv/catalog/products/S79129948/");
        input.add("http://www.prisjakt.nu/produkt.php?p=3711425");
        input.add("http://www.sfbok.se/produkt/halo-the-fall-of-reach-101264");
        input.add("https://www.blocket.se/stockholm/Kymco_super_8_68267339.htm?ca=11&w=1");
        input.add("https://www.kjell.com/se/sortiment/hus-halsa-fritid/dronare/denver-dch-330-dronare-med-hd-kamera-p50866");

        input.add("http://tb.snekabel.se/fel1.html");
        input.add("http://tb.snekabel.se/fel2.html");
        input.add("http://tb.snekabel.se/fel3.html");
        input.add("http://tb.snekabel.se/fel4.html");
        input.add("http://tb.snekabel.se/fel5.html");
        input.add("http://tb.snekabel.se/fel6.html");

        String[] strings = input.stream().toArray(String[]::new);
        answers = Lib.evaluateInput(strings);

        for(String answer : answers)
        {
            System.out.println(answer);
        }

        @SuppressWarnings("unused")
        User user = new User();
        //@SuppressWarnings("unused")
        String[] userA = null;
        try {
            userA = User.parseIRCUser(":TBRPI!~tb@c-795be255.04-35-6875761.cust.bredbandsbolaget.se");
        } catch (Exception e) {
            System.out.println("Cant parse IRC string! " + e.getLocalizedMessage());
        }
        user.setNickname(userA[0]);
        user.setUsername(userA[1]);
        user.setHost(userA[2]);
    }
}
