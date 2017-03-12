package com.lanzuan.website.service.impl;

import com.lanzuan.common.code.NavbarBrandTypeEnum;
import com.lanzuan.common.constant.Constant;
import com.lanzuan.entity.*;
import com.lanzuan.entity.entityfield.Card;
import com.lanzuan.entity.entityfield.CarouselCaption;
import com.lanzuan.entity.entityfield.NavItem;
import com.lanzuan.entity.entityfield.NavbarBrand;
import com.lanzuan.website.service.*;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StartOnLoadService {
    private static Logger logger = LogManager.getLogger();
    @Resource(name = "pageTemplateService")
    private PageTemplateService pageTemplateService;
    @Resource(name = "pageComponentService")
    private PageComponentService pageComponentService;
    @Resource(name = "articleSectionService")
    private ArticleSectionService articleSectionService;
    @Resource(name = "articleService")
    private IArticleService articleService;
    @Resource(name = "carouselService")
    private ICarouselService carouselService;
    @Resource(name = "carouselItemService")
    private ICarouselItemService carouselItemService;
    @Resource(name = "navbarService")
    private INavbarService navbarService;
    @Resource(name = "imageCardGroupService")
    private IImageCardGroupService imageCardGroupService;

    /**
     * Spring 容器初始化时加载
     */
    public void loadData() {
        logger.info("容器初始化完成，载入初始化数据。。。。。。");
        initPageTemplates();
        initPageData();
    }

    private void initPageData() {
        initNavbarData();
        initCarouselData();
        initImageCardGroupData();
        initArticleSectionData();
    }

    private void initImageCardGroupData() {
        logger.info("初始化图文卡片组。。。");
        ImageCardGroup imageCardGroup=imageCardGroupService.findByUri("/home");
        if (imageCardGroup==null){
            logger.info("   未查询到图文卡片组方案，应用默认方案。。。");
            imageCardGroup=new ImageCardGroup();
            imageCardGroup.setEnabled(true);
            imageCardGroup.setUri("/home");
            imageCardGroup.setName("图文卡片组1");
            List<Card> Cards=new ArrayList<Card>();
            Card Card1=new Card();
            Card Card2=new Card();
            Card Card3=new Card();
            Card1.setLink("/statics/page/business/b1.html");
            Card2.setLink("/statics/page/business/b2.html");
            Card3.setLink("/statics/page/business/b3.html");
            Card1.setImage("/statics/image/lanzuan/home/logo-bg.jpg");
            Card2.setImage("/statics/image/lanzuan/home/logo-bg.jpg");
            Card3.setImage("/statics/image/lanzuan/home/logo-bg.jpg");
            Card1.setText("智慧城市");
            Card2.setText("三农服务");
            Card3.setText("软件开发");
            Cards.add(Card1);
            Cards.add(Card2);
            Cards.add(Card3);
            imageCardGroup.setCards(Cards);
            imageCardGroupService.insert(imageCardGroup);
        }

    }

    private void initNavbarData() {
        logger.info("初始化导航条。。。");
        Navbar navbar=navbarService.findByUri("/home");
        if (navbar==null){
            logger.info("   未查询到导航条方案，应用默认方案。。。");
            navbar=new Navbar();
            navbar.setName("首页导航条");
            navbar.setUri("/home");
            navbar.setEnabled(true);
            NavbarBrand navbarBrand=new NavbarBrand();
            navbarBrand.setType(NavbarBrandTypeEnum.IMAGE.toCode());
            navbarBrand.setValue("/statics/image/lanzuan/icons/ico.jpg");
            navbar.setNavbarBrand(navbarBrand);
            List<NavItem> navItems=new ArrayList<NavItem>();
            NavItem navItem1=new NavItem();
            NavItem navItem2=new NavItem();
            NavItem navItem3=new NavItem();
            NavItem navItem4=new NavItem();
            NavItem navItem5=new NavItem();
            navItem1.setName("首页");
            navItem2.setName("智慧城市");
            navItem3.setName("三农服务");
            navItem4.setName("软件开发");
            navItem5.setName("关于我们");
            navItem1.setFaClass("fa-home");
            navItem2.setFaClass("fa-wifi");
            navItem3.setFaClass("fa-tree");
            navItem4.setFaClass("fa-safari");
            navItem5.setFaClass("fa-male");
            navItem1.setLink("/");
            navItem2.setLink("/statics/page/business/b1.html");
            navItem3.setLink("/statics/page/business/b2.html");
            navItem4.setLink("/statics/page/business/b3.html");
            navItem5.setLink("/statics/page/about-us.html");

            navItems.add(navItem1);
            navItems.add(navItem2);
            navItems.add(navItem3);
            navItems.add(navItem4);
            navItems.add(navItem5);
            navbar.setNavItems(navItems);
            navbarService.insert(navbar);
        }
    }

    private void initArticleSectionData() {
        logger.info("初始化文章版块。。。");
        DBObject dbObject=new BasicDBObject();
        dbObject.put("enabled",true);
        List<String> fields=new ArrayList<String>();
        fields.add("id");
        fields.add("name");
        fields.add("image");
        fields.add("articles");
        int limit= Constant.articleSectionNum;
        List<ArticleSection> articleSections=articleSectionService.findFields(dbObject,fields,limit,"createDate",false);
        if (articleSections==null||articleSections.size()==0){
            System.out.println("    未查询到文章版块数据，使用默认内容。。。");
            articleSections=new ArrayList<ArticleSection>();
            ArticleSection articleSection1=new ArticleSection();
            articleSection1.setName("新闻动态");
            Date now=new Date();

            articleSection1.setCreateDate(now);
//            articleSection1.setCreator(getLoginAdministrator(session));
            articleSection1.setEnabled(true);
            Article article1=new Article();
            article1.setDate(now);
            article1.setTitle("携手并进·共赢未来 ——2016蓝钻新媒体云服务商交流大会成功召开");
            article1.setContent("<div class=\"row\">\n" +
                    "                    <h2 id=\"title\">\n" +
                    "                        携手并进·共赢未来 ——2016蓝钻新媒体云服务商交流大会成功召开\n" +
                    "                    </h2>\n" +
                    "                    <em >2016-04-01</em>\n" +
                    "                    <strong class=\"profile_nickname\">蓝钻科技</strong><div class=\"float-right\">\n" +
                    "                    <!-- JiaThis Button BEGIN -->\n" +
                    "                    <div class=\"jiathis_style\">\n" +
                    "                        <span class=\"jiathis_txt\">分享到：</span>\n" +
                    "                        <a class=\"jiathis_button_tools_1\"></a>\n" +
                    "                        <a class=\"jiathis_button_tools_2\"></a>\n" +
                    "                        <a class=\"jiathis_button_tools_3\"></a>\n" +
                    "                        <a class=\"jiathis_button_tools_4\"></a>\n" +
                    "                        <a href=\"http://www.jiathis.com/share\" class=\"jiathis jiathis_txt jiathis_separator jtico jtico_jiathis\" target=\"_blank\">更多</a>\n" +
                    "                        <a class=\"jiathis_counter_style\"></a>\n" +
                    "                    </div>\n" +
                    "                    <script type=\"text/javascript\" src=\"http://v3.jiathis.com/code/jia.js\" charset=\"utf-8\"></script>\n" +
                    "                    <!-- JiaThis Button END -->\n" +
                    "                </div>\n" +
                    "\n" +
                    "\n" +
                    "                    <img type=\"gif\" src=\"/statics/image/lanzuan/news/001.gif\" class=\"img-responsive margin-top-10 margin-bottom-10\"  />\n" +
                    "                2016\n" +
                    "                年3月29日，蓝钻公司2016年新媒体云服务商交流大会在湖南省长沙市宁乡县湘都生态农庄举行，\n" +
                    "                这是蓝钻公司召集全国各地服务商召开的一场别开生面的交流会议。\n" +
                    "                <img type=\"jpeg\" src=\"/statics/image/lanzuan/news/002\" class=\"img-responsive margin-top-10 margin-bottom-10\"  />\n" +
                    "\n" +
                    "                传统媒体与新兴媒体的融合发展\n" +
                    "                毫无疑问\n" +
                    "\n" +
                    "                已经成为时代新课题，如何在内容、技术、经营方式等层面促进新旧媒体的融合发展，是我们一直关心的问题。蓝钻公司作为一家致力打造网络、内容、渠道、应用、\n" +
                    "                技术于一体的移动互联网平台型公司，始终坚持天网融合地网的策略，在媒体融合领域积累了自己独特的发展经验和心得。通过这次会议，蓝钻将此经验与大家分享交流，\n" +
                    "                希望在媒体融合的道路上取得更大的发展。\n" +
                    "                <img type=\"jpeg\" src=\"/statics/image/lanzuan/news/003\" class=\"img-responsive margin-top-10 margin-bottom-10\"  />\n" +
                    "\n" +
                    "                移动互联技术飞速发展的时代，智慧城市的建设成为转变城市发展方式、\n" +
                    "                提升城市发展质量的重要方略。此次会议，蓝钻公司提出以蓝钻云·WiFi作为流量入口助力宁乡县智慧城市的建设，并从宏观立场、业务模式、\n" +
                    "                技术运营等角度提出了发展建议。\n" +
                    "                    <img src=\"/statics/image/lanzuan/news/6401\" class=\"img-responsive margin-top-10 margin-bottom-10\"  />\n" +
                    "                    会上，徐总、营销中心负责人、技术中心负责人、\n" +
                    "                运营中心负责人详细地向与会人员阐述了蓝钻新媒体云平台在各个层面的具体策略，并结合蓝钻公司具体项目实施案例，\n" +
                    "                向大家详尽阐释了什么是蓝钻新媒体云平台以及蓝钻新媒体云平台如何为智慧城市服务的问题。通过蓝钻云·WiFi服务商整合当地所有资源，将资源最大化，\n" +
                    "                让各个机构、商家、用户连接起来，实现效益共享。\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "                <img src=\"/statics/image/lanzuan/news/6403\" class=\"img-responsive margin-top-10 margin-bottom-10\"  />\n" +
                    "                    同时，建立基于数据层的数据中心，以数据为支撑建立业务模块，并重视后续用户需求，对应定向推广，\n" +
                    "                    延伸业务发展方向，创造更多的发展空间。\n" +
                    "\n" +
                    "                <img src=\"/statics/image/lanzuan/news/6404\" class=\"img-responsive margin-top-10 margin-bottom-10\"  />\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "                会上，针对蓝钻云·WiFi的发展问题，不少服务商伙伴提出了中肯的建议，徐总等人认真倾听了大家的意见，并承诺在以后的工作中逐渐落实并改善。\n" +
                    "                    <img src=\"/statics/image/lanzuan/news/6402\" class=\"img-responsive margin-top-10 margin-bottom-10\"  />\n" +
                    "\n" +
                    "                正如徐总在会上所言，将移动互联网的技术、产品、理念融入到传统媒体上，打造成为行业的、可持续发展的生态平台，实现社会效益与经济效益的共赢，\n" +
                    "                才是根本的生存之道。我们衷心希望，在以后的发展道路上，通过更多的交流合作的机会，与更多的合作伙伴一起携手并进、共赢未来！\n" +
                    "                </div>");
            List<Article> articles=new ArrayList<Article>();
            articles.add(article1);
            articleSection1.setArticles(articles);
            articleSections.add(articleSection1);
            ArticleSection articleSection2=new ArticleSection();
            articleSection2.setEnabled(true);
            articleSection2.setName("企业文化");
            Article article2=new Article();
            article2.setTitle("“倾家荡产捐助”与“扎克伯格式慈善”");
            article2.setDate(now);
            article2.setContent("<p class=\"block\">\n" +
                    "                    保洁员卖掉唯一住房，捐助45个孩子。沈阳保洁员赵永久大爷，一家三口租住30余平方米的单间，家里除了两张床一个简易衣柜，没什么值钱物；每月近三分之一的收入用来做慈善，30多年累计帮助45个孩子，累计捐款至少十八九万元。15张荣誉证书、40张收据，记录着沈阳一位保洁员的“大爱”。（2月22日《辽沈晚报》）\n" +
                    "</p><p>\n" +
                    "                    为了捐助他人，连自家唯一的住房都卖了，让老伴和女儿跟着自己租房住，过着清贫而艰苦的生活，堪称是倾家荡产也要捐助，保洁员赵大爷做慈善真是“蛮拼的”。不过，跟其他“穷人慈善”被报道后引发争议一样，公众对赵大爷的评价也出现了两种意见，有人认为他体现了人间大爱，有人却认为他应该先照顾好小家再帮助大家、做慈善要力所能及。\n" +
                    "                </p><p>\n" +
                    "                    实际上，做慈善是天经地义的事情，原本无须大惊小怪；当事人之所以捐款，应该只是发自内心的善良，并没有试图去“感动”谁，更没有想到会引起这么大的“轰动”。很多人喜欢低调慈善，如果媒体和公众由于过度“感动”而影响到了当事人的正常生活，很可能弄巧成拙、得不偿失。\n" +
                    "                </p><p>\n" +
                    "                    几乎每一次“穷人慈善”都会在全社会引发关注，这主要是因为当事人的“穷人”身份。实际上，任何人做慈善都是一样的——都是善心的体现、都对社会有利，没必要去区分他是富人还是穷人，更没必要对其身份大肆渲染。惟有如此，方能做到“慈善面前人人平等”，更好地推动“全民慈善”的实现。\n" +
                    "                </p><p>\n" +
                    "                    过度渲染“穷人慈善”还容易造成“为慈善倾家荡产”的畸形现象。做慈善最重要的原则之一，便是“量力而行”。但现实当中，有些人却并非如此。汶川震灾后，广州的谭先生举着写有“广州市民抗震救灾售卖楼王”的牌子上街，他称自己要把房子卖了，把钱全部捐给灾区，自己一家租房子住；3名已经50多岁的老兵，为帮助灾区把家里唯一值钱的东西甚至粮食都变卖了……且不说他们的做法能给灾区提供的帮助十分有限，单是他们不顾一切的冲动就不值得提倡。我不忍心说他们炒作，但绝不支持这种非理性的盲目慈善。把自己的事情解决好、把自己的日子过好，也是现代公民应当具备的最基本的社会责任之一。现代慈善不需要倾家荡产，更不需要无谓的“自我牺牲”。这样的慈善不具备可持续性，还有可能走进死胡同，造成恶性循环。\n" +
                    "                </p><p>\n" +
                    "                    做慈善最重要的原则之一，便是量力而行、脚踏实地。唯有如此，公益事业才能纳入良性循环的轨道，获得长期的可持续性发展。我不知道赵大爷们的“善行”还能持续多久，我更希望他们及早回到理性的生活轨道上来。\n" +
                    "                </p><p>\n" +
                    "                    几年前，有一则新闻曾引起我的注意。“我一直希望在自己有能力时，为家庭贫困的孩子提供帮助。”山西大学毕业生张文娟一直有个心愿，希望能够用自己的力量帮助一名困难学子。“我现在的工资不高，每个月能拿出200块钱，但我觉得帮助一个上小学的孩子应该够。”张文娟说，除了在物质上帮助孩子，平时两个人还可以互相写信。相对而言，张文娟就很踏实，不好高骛远、心血来潮，而是量力而行、做力所能及的事情。她刚参加工作，收入还不高，如果把大部分钱都拿来捐助，无疑会影响到自己的正常生活。既然暂时没能力资助大学生、中学生，那么就先资助小学生吧。这样做无疑是科学的，能够让自己的爱心获得“可持续性发展”，这份理性也值得学习。\n" +
                    "                </p><p>\n" +
                    "                    做慈善其实并不难，也没有那么复杂，只要有一份爱心，就能找到自己的位置，向需要帮助的人伸出援手。“倾家荡产做慈善”这种走极端的做法，并不值得提倡，同时也折射出当今慈善领域存在的诸多观念问题和现实问题。引导全社会更加客观理性地认识慈善事业，同时完善慈善制度、加强慈善管理，将慈善事业纳入法治化、制度化、理性化的正常轨道，已属当务之急。\n" +
                    "                </p><p>\n" +
                    "                    相对于穷人慈善，富人在公益事业上无疑更有能力，也应当在自愿的基础上承担更多、更大的责任。这让我想到了社交网站“脸谱”（Facebook）创始人扎克伯格，他在宣布妻子产下一女的同时，承诺将他和妻子持有的“脸谱”99%股份（约450亿美元）捐出，用于慈善。\n" +
                    "                </p><p>\n" +
                    "                    生闺女、捐财产，并且还不是捐一星半点，而是捐出绝大部分身家，扎克伯格夫妇无疑值得所有“地球人”点赞。中国也有企业家承诺“裸捐”，比如爱国者总裁冯军几年前在微博上公开表示，自愿在活着的时候将个人全部财产逐步捐献给社会，用于公益和慈善事业。至于他是否在用实际行动兑现承诺，我没有看到更多的后续报道，无法确认。但很显然，能够做出“裸捐”（或捐出大部分财产）的中国富人，目前并不太多。\n" +
                    "                </p><p>\n" +
                    "                    毋庸讳言，部分中国富人的慈善意识跟发达国家相比，还不太成熟。比尔·盖茨就曾呼吁中国富豪多做慈善，别只懂奢侈品。有报道说，中国的富人购买了很多西方国家富翁品味的东西：艺术品、湾流私人飞机、DRC葡萄酒和爱玛仕手袋。但微软联合创始人比尔·盖茨认为，他们还没有接受一个最重要的东西，那就是慈善。他接受采访时呼吁中国的富人多做慈善，认为中国缺乏系统性慈善行为。\n" +
                    "                </p><p>\n" +
                    "                    对于慈善这点事儿，比尔·盖茨很有发言权。他不仅富可敌国、多次蝉联世界首富，同时更是一位值得尊敬的大慈善家。他具体捐了多少钱、做了多少善事，恐怕很难给出具体数字——他一直不停地在捐，捐款数额不断被自己刷新。况且，像比尔·盖茨这样把做慈善当成自己生活和事业一部分的人，已无需纠结具体的捐赠数额，重要的是永葆那份善心，并从中得到满足和慰藉。\n" +
                    "                </p><p>\n" +
                    "                    比尔·盖茨呼吁中国富豪多做慈善，既是善意的提醒，也是委婉的批评。扎克伯格夫妻也用自己的实际行动，为富人们树立了榜样。中国某些富人富则富矣，惜乎富的只是口袋，而非“脑袋”，更非心灵。他们更热衷于享受金钱和物质所带来的生理快感，如果说也有一些心理层面的追求，则更多地体现在虚荣心等低层次阶段。抢购奢侈品、婚丧嫁娶奢靡无度等等，都是典型表现。有的还跑到国外去炫富，一副暴发户的浅薄嘴脸，令人侧目。而将财富留给后人的传统小农意识，让某些富人成了守财奴。比尔·盖茨、扎克伯格也是富人，并且是世界上最富的富人之一，他们的呼吁和行动或许可以给某些小富即安甚至不可一世的富人们带来启发：该如何对待财富？该如何摆正心态？怎么花钱是个人的权利，但怎么花钱同样也足以体现出人的品味和道德素养。跟买奢侈品等个人享受比起来，做慈善是更高尚行为，能带给人更高层次的心灵愉悦。\n" +
                    "                </p><p>\n" +
                    "                    西方流行一句话：一夜之间可以造就一个富翁，但是要培养一个绅士却要三代人的努力。某些中国富人需要学习的东西还有很多很多。同时，鼓励“先富起来”的人多做慈善，不仅需要富人们提高自身素质，还需要政府的引导和法制的保护。一方面，要制订经济方面的激励政策来引导富人做慈善，比如免税或减税等；同时，更要保证慈善事业的公开透明和严格监管。西方发达国家有着成熟的慈善文化，更有着发达的慈善机构和完善的配套制度，公众捐款不必担心自己的善款不能被善用。而这些正是中国所欠缺的。我的捐款有多少真正用到了需要帮助的人身上？到底有多少比例的善款支付了相关机构的巨额“管理费”？拿到善款，相关地方是为老百姓办实事了还是兴高采烈地采购豪华越野车去了？……这些疑问都让某些中国人捂紧自己的钱袋子，不敢做善事、不愿做善事。完善慈善制度、加强慈善管理，将慈善事业纳入法制化、制度化、理性化的正常轨道，慈善事业才能释放活力，包括富人们在内的所有人的爱心才能有处安放。\n" +
                    "                </p><p>\n" +
                    "                    至于饱受诟病的“为富不仁”现象，其实是个伪问题。捐不捐款是个人的自由，并不能代表一个人是否“仁义”。做慈善也并非富人回报社会的惟一途径，或许人家有更好的方式去回报社会、帮助别人，例如扩大投资为社会创造更多的就业机会等等。对“穷人慈善”的宣传再轰轰烈烈，恐怕也不一定真能带动多少富人投身慈善，何况类似的做法还有“道德绑架”之嫌，并不足取。少一些“倾家荡产捐助”、多一些“扎克伯格式慈善”，才是慈善事业的应有之义。\n" +
                    "                </p>");
            articles.add(article2);
            articleSection2.setArticles(articles);
            articleSections.add(articleSection2);
            ArticleSection articleSection3=new ArticleSection();
            articleSection3.setName("活动专题");
            articleSection3.setImage("/statics/image/lanzuan/home/huodongzhuanti.png");
            articleSections.add(articleSection3);
            articleService.insertAll(articles);
            articleSectionService.insertAll(articleSections);

        }
    }

    private void initCarouselData() {
        logger.info("初始化轮播图。。。");
        Carousel carousel=carouselService.findCarouselByUri("/home");
        if (carousel==null||carousel.getId()==null){
            logger.info("   未查询到轮播图方案，应用默认方案。。。");
            carousel=new Carousel();
            carousel.setName("首页轮播图");
            carousel.setDate(new Date());
            carousel.setUri("/home");
            carousel.setEnabled(true);
            List<CarouselItem> carouselItems=new ArrayList<CarouselItem>();
            CarouselCaption caption1=new CarouselCaption();
            caption1.setType("link");
            caption1.setText("了解更多");
            caption1.setValue("/statics/page/business/b1.html");
            CarouselItem carouselItem1=new CarouselItem();
            carouselItem1.setValue("/statics/image/lanzuan/home/carousel1.jpg");
            carouselItem1.setType("image");
            carouselItem1.setCarouselCaption(caption1);

            carouselItems.add(carouselItem1);


            CarouselCaption caption2=new CarouselCaption();
            caption2.setType("link");
            caption2.setText("了解更多");
            caption2.setValue("/statics/page/business/b2.html");
            CarouselItem carouselItem2=new CarouselItem();
            carouselItem2.setValue("/statics/image/lanzuan/home/carousel2.jpg");
            carouselItem2.setType("image");
            carouselItem2.setCarouselCaption(caption2);
            carouselItems.add(carouselItem2);

            CarouselCaption caption3=new CarouselCaption();
            caption3.setType("link");
            caption3.setText("了解更多");
            caption3.setValue("/statics/page/business/b2.html");
            CarouselItem carouselItem3=new CarouselItem();
            carouselItem3.setValue("/statics/image/lanzuan/home/carousel3.jpg");
            carouselItem3.setType("image");
            carouselItem3.setCarouselCaption(caption3);
            carouselItems.add(carouselItem3);

            CarouselCaption caption4=new CarouselCaption();
            caption4.setType("link");
            caption4.setText("了解更多");
            caption4.setValue("/statics/page/business/b2.html");
            CarouselItem carouselItem4=new CarouselItem();
            carouselItem4.setValue("/statics/image/lanzuan/home/carousel4.jpg");
            carouselItem4.setType("image");
            carouselItem4.setCarouselCaption(caption4);
            carouselItems.add(carouselItem4);
            carouselItemService.insertAll(carouselItems);
            carousel.setCarouselItems(carouselItems);
            carouselService.insert(carousel);
        }
    }

    private void initPageTemplates() {
        initHomePageTemplates();

    }



    private void initHomePageTemplates() {
        PageTemplate pageTemplate = pageTemplateService.findByUri("/home");
        if (pageTemplate==null){
            System.out.println("没有找到页面模板，应用默认模板......");
            pageTemplate=new PageTemplate();
            PageComponent pageComponent1=new PageComponent();
            pageComponent1.setUri("/statics/page/included/component/navbar/navbar-md-down-fix-bottom.html");
            pageComponent1.setEditUri("/statics/page/included/component/navbar/navbar-md-down-fix-bottom-edit.jsp");
            pageComponent1.setName("响应式导航条模板1");
            pageComponent1.setRemark("在中等及更小屏幕上会固定底部显示。");

            PageComponent pageComponent2=new PageComponent();
            pageComponent2.setUri("/statics/page/included/component/carousel/carousel-full-width-1.html");
            pageComponent2.setEditUri("/statics/page/included/component/carousel/carousel-full-width-1-edit.jsp");
            pageComponent2.setName("响应式轮播图");
            pageComponent2.setRemark("任何设备及屏幕都为全屏宽度。");

            PageComponent pageComponent3=new PageComponent();
            pageComponent3.setUri("/statics/page/included/component/card-group/img-card-group-1.html");
            pageComponent3.setEditUri("/statics/page/included/component/card-group/img-card-group-1-edit.jsp");
            pageComponent3.setName("图文卡片组模板1");
            pageComponent3.setRemark("一组带文字的图标组，在任何尺寸屏幕下皆保持一行。");

            PageComponent pageComponent4=new PageComponent();
            pageComponent4.setUri("/statics/page/included/lanzuan/collapse-image-title-text-1.html");
            pageComponent4.setEditUri("/statics/page/included/lanzuan/collapse-image-title-text-1-edit.jsp");
            pageComponent4.setName("蓝钻鼠标掠过类似手风琴模板1");
            pageComponent4.setRemark("非标准bootstrap组件，需要依赖angularjs，效果为鼠标掠过按钮，在下方显示相应系列图片。在中等及以上屏幕每行显示4张图片，图片无边框效果；中等以下显示2张图片，图片带圆角相框效果。");

            PageComponent pageComponent5=new PageComponent();
            pageComponent5.setUri("/statics/page/included/lanzuan/article-section-1.html");
            pageComponent5.setEditUri("/statics/page/included/lanzuan/article-section-1-edit.jsp");
            pageComponent5.setName("文章块组件1");
            pageComponent5.setRemark("文章块组件，中等及以下屏幕每行显示一列文字；中等以上每行显示3列新闻。");

            PageComponent pageComponent6=new PageComponent();
            pageComponent6.setUri("/statics/page/included/lanzuan/full-width-image-1.html");
            pageComponent6.setEditUri("/statics/page/included/lanzuan/full-width-image-1-edit.jsp");
            pageComponent6.setName("全屏宽度图片模板1");
            pageComponent6.setRemark("简单的全屏宽度图片。");

            PageComponent pageComponent7=new PageComponent();
            pageComponent7.setUri("/statics/page/included/lanzuan/sort-link-section-1.html");
            pageComponent7.setEditUri("/statics/page/included/lanzuan/sort-link-section-1-edit.jsp");
            pageComponent7.setName("分类链接模板1");
            pageComponent7.setRemark("分类链接模板，将许多链接分为多列排列，每列有个分类名称。在中等及以下屏幕每行显示2列；中等以上每行显示5列。");
            List<PageComponent> pageComponentList=new ArrayList<PageComponent>();
            pageComponentList.add(pageComponent1);
            pageComponentList.add(pageComponent2);
            pageComponentList.add(pageComponent3);
            pageComponentList.add(pageComponent4);
            pageComponentList.add(pageComponent5);
            pageComponentList.add(pageComponent6);
            pageComponentList.add(pageComponent7);
            pageTemplate.setPageComponents(pageComponentList);
            pageTemplate.setUri("/home");
            pageTemplate.setActive(true);
            pageComponentService.insertAll(pageComponentList);

            pageTemplateService.insert(pageTemplate);
        }

    }


}