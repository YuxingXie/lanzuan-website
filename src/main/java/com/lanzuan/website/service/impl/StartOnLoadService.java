package com.lanzuan.website.service.impl;

import com.lanzuan.common.code.NavbarBrandTypeEnum;
import com.lanzuan.common.constant.Constant;
import com.lanzuan.entity.*;
import com.lanzuan.entity.entityfield.*;
import com.lanzuan.support.vo.Image;
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
    @Resource(name = "webPageService")
    private WebPageService webPageService;
    @Resource(name = "pageComponentService")
    private PageComponentService pageComponentService;
    @Resource(name = "sortLinkGroupService")
    private SortLinkGroupService sortLinkGroupService;
    @Resource(name = "articleService")
    private IArticleService articleService;
    @Resource(name = "carouselService")
    private ICarouselService carouselService;
    @Resource(name = "carouselItemService")
    private ICarouselItemService carouselItemService;
    @Resource(name = "navbarService")
    private INavbarService navbarService;
    @Resource(name = "cardGroupService")
    private ICardGroupService cardGroupService;
    @Resource(name = "imageTextBlockGroupService")
    private IImageTextBlockGroupService imageTextBlockGroupService;
    @Resource(name = "fullWidthImageService")
    private IFullWidthImageService fullWidthImageService;
    /**
     * Spring 容器初始化时加载
     */
    public void loadData() {
        logger.info("容器初始化完成，载入初始化数据。。。。。。");
        initWebPages();
        initPageData();
    }

    private void initPageData() {
        initHomePageData();
    }

    private void initHomePageData() {
        initNavbarData();
        initCarouselData();
        initImageTextBlockGroupData();
        initFullWidthImageData();
        initCardGroupData();
        initArticleSectionData();
        initSortLinkGroupData();

    }

    private void initSortLinkGroupData() {
        SortLinkGroup sortLinkGroup=sortLinkGroupService.findByUri("/home", 1);
        Date now=new Date();
        if (sortLinkGroup!=null)
               return;
        sortLinkGroup=new SortLinkGroup();
        SortLink sortLink1=new SortLink();
        SortLink sortLink2=new SortLink();
        SortLink sortLink3=new SortLink();
        SortLink sortLink4=new SortLink();
        sortLink1.setSortName("新闻动态");
        sortLink2.setSortName("售后支持");
        sortLink3.setSortName("如何购买");
        sortLink4.setSortName("合作伙伴");
        List<Link> links1=new ArrayList<Link>();
        List<Link> links2=new ArrayList<Link>();
        List<Link> links3=new ArrayList<Link>();
        List<Link> links4=new ArrayList<Link>();
        Link link1_1=new Link();
        Link link1_2=new Link();
        Link link1_3=new Link();
        Link link1_4=new Link();
        link1_1.setHref("/statics/page/building.html");
        link1_1.setText("荣誉奖项");
        link1_1.setDate(now);
        link1_2.setHref("/statics/page/building.html");
        link1_2.setText("新闻报道");
        link1_2.setDate(now);
        link1_3.setHref("/statics/page/building.html");
        link1_3.setText("活动专题");
        link1_3.setDate(now);
        link1_4.setHref("/statics/page/building.html");
        link1_4.setText("人才招聘");
        link1_4.setDate(now);
        links1.add(link1_1);
        links1.add(link1_2);
        links1.add(link1_3);
        links1.add(link1_4);


        Link link2_1=new Link();
        Link link2_2=new Link();
        Link link2_3=new Link();
        Link link2_4=new Link();
        link2_1.setHref("/statics/page/building.html");
        link2_1.setText("在线咨询（售后）");
        link2_1.setDate(now);
        link2_2.setHref("/statics/page/building.html");
        link2_2.setText("资料库");
        link2_2.setDate(now);
        link2_3.setHref("/statics/page/building.html");
        link2_3.setText("软件下载");
        link2_3.setDate(now);
        link2_4.setHref("/statics/page/building.html");
        link2_4.setText("建议反馈");
        link2_4.setDate(now);
        links2.add(link2_1);
        links2.add(link2_2);
        links2.add(link2_3);
        links2.add(link2_4);

        Link link3_1=new Link();
        Link link3_2=new Link();
        Link link3_3=new Link();

        link3_1.setHref("/statics/page/building.html");
        link3_1.setText("在线咨询（售前）");
        link3_1.setDate(now);
        link3_2.setHref("/statics/page/building.html");
        link3_2.setText("申请试用");
        link3_2.setDate(now);
        link3_3.setHref("/statics/page/building.html");
        link3_3.setText("联系我们");
        link3_3.setDate(now);

        links3.add(link3_1);
        links3.add(link3_2);
        links3.add(link3_3);


        Link link4_1=new Link();
        Link link4_2=new Link();
        Link link4_3=new Link();
        Link link4_4=new Link();
        Link link4_5=new Link();
        link4_1.setHref("/statics/page/building.html");
        link4_1.setText("渠道政策");
        link4_1.setDate(now);
        link4_2.setHref("/statics/page/building.html");
        link4_2.setText("合作申请");
        link4_2.setDate(now);
        link4_3.setHref("/statics/page/building.html");
        link4_3.setText("渠道公告");
        link4_3.setDate(now);
        link4_4.setHref("/statics/page/building.html");
        link4_4.setText("英博智能");
        link4_4.setDate(now);
        link4_5.setHref("/statics/page/building.html");
        link4_5.setText("培训公告");
        link4_5.setDate(now);
        links4.add(link4_1);
        links4.add(link4_2);
        links4.add(link4_3);
        links4.add(link4_4);
        links4.add(link4_5);
        List<SortLink> sortLinks=new ArrayList<SortLink>();

        sortLink1.setLinks(links1);
        sortLink2.setLinks(links2);
        sortLink3.setLinks(links3);
        sortLink4.setLinks(links4);
        sortLinks.add(sortLink1);
        sortLinks.add(sortLink2);
        sortLinks.add(sortLink3);
        sortLinks.add(sortLink4);
        sortLinkGroup.setItems(sortLinks);
        sortLinkGroup.setUri("/home");
        sortLinkGroup.setCreateDate(now);
        sortLinkGroup.setName("默认方案");
        sortLinkGroup.setIndexOfPage(1);
        sortLinkGroup.setEnabled(true);
        sortLinkGroupService.insert(sortLinkGroup);

    }

    private void initFullWidthImageData() {
        logger.info("初始化全屏宽度图片。。。");
        FullWidthImage fullWidthImage=fullWidthImageService.findByUri("/home");
        if (fullWidthImage==null){
            logger.info("   未查询到全屏宽度图片方案，应用默认方案。。。");
            fullWidthImage=new FullWidthImage();
            fullWidthImage.setName("政府大楼全屏宽度图");
            fullWidthImage.setEnabled(true);
            fullWidthImage.setUri("/home");
            Image image=new Image();
            image.setUri("/statics/image/lanzuan/full-width/zfdl.jpg");
            fullWidthImage.setImage(image);
            fullWidthImageService.insert(fullWidthImage);
        }
    }

    private void initImageTextBlockGroupData() {
        logger.info("初始化图文块组。。。");
        ImageTextBlockGroup group=imageTextBlockGroupService.findByUri("/home");
        if (group==null){
            logger.info("   未查询到图文块组方案，应用默认方案。。。");
            group=new ImageTextBlockGroup();
            group.setUri("/home");
            group.setName("ImageTextBlockGroup One");
            group.setText("典型应用");
            group.setEnabled(true);

            List<ImageTextBlock> imageTextBlocks=new ArrayList<ImageTextBlock>();
            ImageTextBlock block1=new ImageTextBlock();
            ImageTextBlock block2=new ImageTextBlock();
            ImageTextBlock block3=new ImageTextBlock();
            ImageTextBlock block4=new ImageTextBlock();
            block1.setName("智慧城市");
            block2.setName("三农服务");
            block3.setName("软件开发");
            block4.setName("运营商");
            List<ImageTextItem> imageTextItems1=new ArrayList<ImageTextItem>();
            List<ImageTextItem> imageTextItems2=new ArrayList<ImageTextItem>();
            List<ImageTextItem> imageTextItems3=new ArrayList<ImageTextItem>();
            List<ImageTextItem> imageTextItems4=new ArrayList<ImageTextItem>();
            ImageTextItem imageTextItem1_1=new ImageTextItem();
            ImageTextItem imageTextItem2_1=new ImageTextItem();
            ImageTextItem imageTextItem3_1=new ImageTextItem();
            ImageTextItem imageTextItem4_1=new ImageTextItem();
            ImageTextItem imageTextItem1_2=new ImageTextItem();
            ImageTextItem imageTextItem2_2=new ImageTextItem();
            ImageTextItem imageTextItem3_2=new ImageTextItem();
            ImageTextItem imageTextItem4_2=new ImageTextItem();
            ImageTextItem imageTextItem1_3=new ImageTextItem();
            ImageTextItem imageTextItem2_3=new ImageTextItem();
            ImageTextItem imageTextItem3_3=new ImageTextItem();
            ImageTextItem imageTextItem4_3=new ImageTextItem();
            ImageTextItem imageTextItem1_4=new ImageTextItem();
            ImageTextItem imageTextItem2_4=new ImageTextItem();
            ImageTextItem imageTextItem3_4=new ImageTextItem();
            ImageTextItem imageTextItem4_4=new ImageTextItem();
            imageTextItem1_1.setText("宁乡县政府WIFI覆盖应用");
            imageTextItem1_1.setImage("/statics/image/lanzuan/280-180/zfdl.jpg");
            imageTextItem1_1.setTitle("宁乡县政府");
            imageTextItem1_1.setLink("/statics/page/business/b1.html");

            imageTextItem1_2.setText("金州开发区创业大楼WIFI覆盖应用");
            imageTextItem1_2.setImage("/statics/image/lanzuan/280-180/cydl.jpg");
            imageTextItem1_2.setTitle("金州开发区创业大楼");
            imageTextItem1_2.setLink("/statics/page/business/b1.html");

            imageTextItem1_3.setText("宁乡县市民之家WIFI应用");
            imageTextItem1_3.setImage("/statics/image/lanzuan/280-180/smzj.jpg");
            imageTextItem1_3.setTitle("宁乡县市民之家");
            imageTextItem1_3.setLink("/statics/page/business/b1.html");

            imageTextItem1_4.setText("宁乡县公安局WIFI覆盖应用");
            imageTextItem1_4.setImage("/statics/image/lanzuan/280-180/nxxgaj.jpg");
            imageTextItem1_4.setTitle("宁乡县公安局");
            imageTextItem1_4.setLink("/statics/page/business/b1.html");

            imageTextItem2_1.setText("葡萄园产业基地");
            imageTextItem2_1.setImage("/statics/image/lanzuan/280-180/putao.jpg");
            imageTextItem2_1.setTitle("有机肥基地");
            imageTextItem2_1.setLink("/statics/page/business/b2.html");

            imageTextItem2_2.setText("葡萄园产业基地");
            imageTextItem2_2.setImage("/statics/image/lanzuan/280-180/putao2.jpg");
            imageTextItem2_2.setTitle("有机肥农业成果");
            imageTextItem2_2.setLink("/statics/page/business/b2.html");

            imageTextItem2_3.setText("双江口桔园产业基地");
            imageTextItem2_3.setImage("/statics/image/lanzuan/280-180/juzi.jpg");
            imageTextItem2_3.setTitle("双江口桔园");
            imageTextItem2_3.setLink("imageTextItem2_3.setLink(/statics/page/business/b2.html)");

            imageTextItem2_4.setText("葡萄园产业基地");
            imageTextItem2_4.setImage("/statics/image/lanzuan/280-180/putaoyuan5.jpg");
            imageTextItem2_4.setTitle("有机肥农业成果");
            imageTextItem2_4.setLink("/statics/page/business/b2.html");

            imageTextItem3_1.setText("宁乡县公安局公众号");
            imageTextItem3_1.setImage("/statics/image/lanzuan/280-180/app1.jpg");
            imageTextItem3_1.setTitle("宁乡县公安局公众号");
            imageTextItem3_1.setLink("/statics/page/business/b3.html");

            imageTextItem3_2.setText("响应式网站、分布式web应用程序");
            imageTextItem3_2.setImage("/statics/image/lanzuan/280-180/webapp.jpg");
            imageTextItem3_2.setTitle("响应式网站、分布式web应用程序");
            imageTextItem3_2.setLink("/statics/page/business/b3.html");

            imageTextItem3_3.setText("警民互动平台");
            imageTextItem3_3.setImage("/statics/image/lanzuan/280-180/smzj.jpg");
            imageTextItem3_3.setTitle("警民互动平台");
            imageTextItem3_3.setLink("/statics/page/business/b3.html");

            imageTextItem3_4.setText("腾讯软件服务");
            imageTextItem3_4.setImage("/statics/image/lanzuan/280-180/tengxunwang.jpg");
            imageTextItem3_4.setTitle("腾讯软件服务");
            imageTextItem3_4.setLink("/statics/page/business/b3.html");

            imageTextItem4_1.setText("中国电信");
            imageTextItem4_1.setImage("/statics/image/lanzuan/280-180/zgdx.jpg");
            imageTextItem4_1.setTitle("中国电信");
            imageTextItem4_1.setLink("/statics/page/business/b3.html");

            imageTextItem4_2.setText("中国移动");
            imageTextItem4_2.setImage("/statics/image/lanzuan/280-180/zgyd.jpg");
            imageTextItem4_2.setTitle("中国移动");
            imageTextItem4_2.setLink("/statics/page/business/b3.html");

            imageTextItem4_3.setText("中国联通");
            imageTextItem4_3.setImage("/statics/image/lanzuan/280-180/zglt.jpg");
            imageTextItem4_3.setTitle("中国联通");
            imageTextItem4_3.setLink("/statics/page/business/b3.html");

            imageTextItem4_4.setText("中国联通");
            imageTextItem4_4.setImage("/statics/image/lanzuan/280-180/zglt.jpg");
            imageTextItem4_4.setTitle("中国联通");
            imageTextItem4_4.setLink("/statics/page/business/b3.html");

            imageTextItems1.add(imageTextItem1_1);
            imageTextItems1.add(imageTextItem1_2);
            imageTextItems1.add(imageTextItem1_3);
            imageTextItems1.add(imageTextItem1_4);

            imageTextItems2.add(imageTextItem2_1);
            imageTextItems2.add(imageTextItem2_2);
            imageTextItems2.add(imageTextItem2_3);
            imageTextItems2.add(imageTextItem2_4);

            imageTextItems3.add(imageTextItem3_1);
            imageTextItems3.add(imageTextItem3_2);
            imageTextItems3.add(imageTextItem3_3);
            imageTextItems3.add(imageTextItem3_4);

            imageTextItems4.add(imageTextItem4_1);
            imageTextItems4.add(imageTextItem4_2);
            imageTextItems4.add(imageTextItem4_3);
            imageTextItems4.add(imageTextItem4_4);


            block1.setImageTextItems(imageTextItems1);
            block2.setImageTextItems(imageTextItems2);
            block3.setImageTextItems(imageTextItems3);
            block4.setImageTextItems(imageTextItems4);

            imageTextBlocks.add(block1);
            imageTextBlocks.add(block2);
            imageTextBlocks.add(block3);
            imageTextBlocks.add(block4);
            group.setItems(imageTextBlocks);
            imageTextBlockGroupService.insert(group);
        }
    }

    private void initCardGroupData() {
        logger.info("初始化图文卡片组。。。");
        CardGroup cardGroup=cardGroupService.findByUri("/home");
        if (cardGroup==null){
            logger.info("   未查询到图文卡片组方案，应用默认方案。。。");
            cardGroup=new CardGroup();
            cardGroup.setEnabled(true);
            cardGroup.setUri("/home");
            cardGroup.setName("图文卡片组1");
            List<Card> Cards=new ArrayList<Card>();
            Card Card1=new Card();
            Card Card2=new Card();
            Card Card3=new Card();
            Card1.setLink("/statics/page/business/b1.html");
            Card2.setLink("/statics/page/business/b2.html");
            Card3.setLink("/statics/page/business/b3.html");
            Card1.setImage("/statics/image/cardGroup/logo-bg.jpg");
            Card2.setImage("/statics/image/cardGroup/logo-bg.jpg");
            Card3.setImage("/statics/image/cardGroup/logo-bg.jpg");
            Card1.setText("智慧城市");
            Card2.setText("三农服务");
            Card3.setText("软件开发");
            Cards.add(Card1);
            Cards.add(Card2);
            Cards.add(Card3);
            cardGroup.setItems(Cards);
            cardGroupService.insert(cardGroup);
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
            navbar.setItems(navItems);
            navbarService.insert(navbar);
        }
    }

    private void initArticleSectionData() {
        logger.info("初始化文章版块。。。");
        DBObject dbObject=new BasicDBObject();
        dbObject.put("enabled",true);
        List<String> fields=new ArrayList<String>();
//        fields.add("id");
//        fields.add("name");
//        fields.add("image");
//        fields.add("articles");
        int limit= Constant.ARTICLE_SECTION_NUM;
        SortLinkGroup sortLinkGroup =sortLinkGroupService.findByUri("/home");
        if (sortLinkGroup ==null|| sortLinkGroup.getItems()==null &&sortLinkGroup.getItems().size()==0){
            System.out.println("    未查询到文章版块数据，使用默认内容。。。");
            sortLinkGroup =new SortLinkGroup();
            sortLinkGroup.setEnabled(true);
            sortLinkGroup.setUri("/home");
            sortLinkGroup.setName("默认方案");
            sortLinkGroup.setIndexOfPage(0);
            Date now=new Date();

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
            articleService.insertAll(articles);



            List<SortLink> sortLinks=new ArrayList<SortLink>();
            SortLink sortLink1 =new SortLink();
            sortLink1.setSortName("新闻动态");
            List<Link> links1=new ArrayList<Link>();
            Link link1=new Link();
            link1.setDate(article1.getDate());
            link1.setHref("/article/" + article1.getId());
            link1.setText(article1.getTitle());
            links1.add(link1);
            sortLink1.setLinks(links1);
            sortLinks.add(sortLink1);


            SortLink sortLink2 =new SortLink();
            sortLink2.setSortName("企业文化");
            List<Link> links2=new ArrayList<Link>();
            Link link2=new Link();
            link2.setDate(article2.getDate());
            link2.setHref("/article/" + article2.getId());
            link2.setText(article1.getTitle());
            links2.add(link2);
            sortLink2.setLinks(links2);
            sortLinks.add(sortLink2);

            SortLink sortLink3=new SortLink();
            sortLink3.setImage("/statics/image/lanzuan/home/huodongzhuanti.png");
            sortLink3.setSortName("活动专题");
            sortLink3.setImageHref("/statics/image/lanzuan/home/huodongzhuanti.png");
            sortLinks.add(sortLink3);
            sortLinkGroup.setItems(sortLinks);

            sortLinkGroupService.insert(sortLinkGroup);

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
            carousel.setItems(carouselItems);
            carouselService.insert(carousel);
        }
    }

    private void initWebPages() {
        initHomePage();

    }



    private void initHomePage() {
        WebPage webPage = webPageService.findByUri("/home");
        if (webPage==null){
            logger.info("没有找到 /home 页面定义，应用默认定义......");
            webPage=new WebPage();
            PageComponent pageComponent1=new PageComponent();
            PageComponent pageComponent2=new PageComponent();
            PageComponent pageComponent3=new PageComponent();
            PageComponent pageComponent4=new PageComponent();
            PageComponent pageComponent5=new PageComponent();
            PageComponent pageComponent6=new PageComponent();
            PageComponent pageComponent7=new PageComponent();
            pageComponent1.setName("响应式导航条模板1");
            pageComponent2.setName("响应式轮播图");
            pageComponent3.setName("图文卡片组模板1");
            pageComponent4.setName("蓝钻鼠标掠过类似手风琴模板1");
            pageComponent5.setName("文章块组件1");
            pageComponent6.setName("全屏宽度图片模板1");
            pageComponent7.setName("分类链接模板1");

            pageComponent1.setRemark("在中等及更小屏幕上会固定底部显示。");
            pageComponent2.setRemark("任何设备及屏幕都为全屏宽度。");
            pageComponent3.setRemark("一组带文字的图标组，在任何尺寸屏幕下皆保持一行。");
            pageComponent4.setRemark("非标准bootstrap组件，需要依赖angularjs，效果为鼠标掠过按钮，在下方显示相应系列图片。在中等及以上屏幕每行显示4张图片，图片无边框效果；中等以下显示2张图片，图片带圆角相框效果。");
            pageComponent5.setRemark("文章块组件，中等及以下屏幕每行显示一列文字；中等以上每行显示3列新闻。");
            pageComponent6.setRemark("简单的全屏宽度图片。");
            pageComponent7.setRemark("分类链接模板，将许多链接分为多列排列，每列有个分类名称。在中等及以下屏幕每行显示2列；中等以上每行显示5列。");

            pageComponent1.setTemplateUri("/statics/page/included/component/navbar/navbar-md-down-fix-bottom.jsp");
            pageComponent2.setTemplateUri("/statics/page/included/component/carousel/carousel-full-width-1.jsp");
            pageComponent3.setTemplateUri("/statics/page/included/component/card-group/img-card-group-1.jsp");
            pageComponent4.setTemplateUri("/statics/page/included/lanzuan/collapse-image-title-text-1.jsp");
            pageComponent5.setTemplateUri("/statics/page/included/lanzuan/article-section-1.jsp");
            pageComponent6.setTemplateUri("/statics/page/included/lanzuan/full-width-image-1.jsp");
            pageComponent7.setTemplateUri("/statics/page/included/lanzuan/sort-link-section-1.jsp");

            pageComponent1.setEditUri("/statics/page/included/component/navbar/navbar-md-down-fix-bottom-edit.jsp");
            pageComponent2.setEditUri("/statics/page/included/component/carousel/carousel-full-width-1-edit.jsp");
            pageComponent3.setEditUri("/statics/page/included/component/card-group/img-card-group-1-edit.jsp");
            pageComponent4.setEditUri("/statics/page/included/lanzuan/collapse-image-title-text-1-edit.jsp");
            pageComponent5.setEditUri("/statics/page/included/lanzuan/article-section-1-edit.jsp");
            pageComponent6.setEditUri("/statics/page/included/lanzuan/full-width-image-1-edit.jsp");
            pageComponent7.setEditUri("/statics/page/included/lanzuan/article-section-1-edit.jsp");

            pageComponent1.setJsonVariableName("navbar");
            pageComponent2.setJsonVariableName("carousel");
            pageComponent3.setJsonVariableName("cardGroup");
            pageComponent4.setJsonVariableName("imageTextBlockGroup");
            pageComponent5.setJsonVariableName("sortLinkGroup");
            pageComponent6.setJsonVariableName("fullWidthImage");
            pageComponent7.setJsonVariableName("bottomSortLinkGroup");

            pageComponent1.setDataUri("/navbar/home/data");
            pageComponent2.setDataUri("/carousel/home/data");
            pageComponent3.setDataUri("/card-group/home/data");
            pageComponent4.setDataUri("/image-text-block-group/home/data");
            pageComponent5.setDataUri("/sort-link-group/data");
            pageComponent6.setDataUri("/full-width-image/home/data");
            pageComponent7.setDataUri("/sort-link-group/bottom/data");

            pageComponent1.setToggleUri("/admin/navbar/status-change");
            pageComponent2.setToggleUri("/admin/carousel/update");
            pageComponent3.setToggleUri("/admin/card-group/status-change");
            pageComponent4.setToggleUri("/admin/image-text-block-group/status-change");
            pageComponent5.setToggleUri("/admin/sort-link-group/status-change");
            pageComponent6.setToggleUri("/admin/full-width-image/status-change");
//            pageComponent7.setToggleUri("/admin/sort-link-group/bottom/status-change");
            pageComponent7.setToggleUri("/admin/sort-link-group/status-change");

            pageComponent1.setDeleteUri("/admin/navbar/delete/");
            pageComponent2.setDeleteUri("/admin/carousel/delete/");
            pageComponent3.setDeleteUri("/admin/card-group/delete/");
            pageComponent4.setDeleteUri("/admin/image-text-block-group/delete/");
            pageComponent5.setDeleteUri("/admin/sort-link-group/delete/");
            pageComponent6.setDeleteUri("/admin/full-width-image/delete/");
//            pageComponent7.setDeleteUri("/admin/sort-link-group/bottom/delete/");
            pageComponent7.setDeleteUri("/admin/full-width-image/delete/");

            pageComponent1.setSaveUri("/admin/navbar/update");
            pageComponent2.setSaveUri("/admin/carousel/insert-all");
            pageComponent3.setSaveUri("/admin/card-group/update");
            pageComponent4.setSaveUri("/admin/image-text-block-group/update");
            pageComponent5.setSaveUri("/admin/sort-link-group/new");
            pageComponent6.setSaveUri("/admin/full-width-image/update");
//            pageComponent7.setSaveUri("/admin/sort-link-group/bottom/update");
            pageComponent7.setSaveUri("/admin/sort-link-group/new");

            pageComponent1.setListOperationUri("/admin/navbar/list-page/");
            pageComponent2.setListOperationUri("/admin/carousel/list-page/");
            pageComponent3.setListOperationUri("/admin/card-group/list-page/");
            pageComponent4.setListOperationUri("/admin/image-text-block-group/list-page/");
            pageComponent5.setListOperationUri("/admin/sort-link-group/list-page/");
            pageComponent6.setListOperationUri("/admin/full-width-image/list-page/");
            pageComponent7.setListOperationUri("/admin/sort-link-group/list-page/");
//            pageComponent7.setListOperationUri("/admin/sort-link-group/list-page/");

            pageComponent1.setListDataUri("/admin/navbar/list/data");
            pageComponent2.setListDataUri("/admin/carousel/list/data");
            pageComponent3.setListDataUri("/admin/card-group/list/data");
            pageComponent4.setListDataUri("/admin/image-text-block-group/list/data");
            pageComponent5.setListDataUri("/admin/sort-link-group/list/data");
            pageComponent6.setListDataUri("/admin/full-width-image/list/data");
            pageComponent7.setListDataUri("/admin/sort-link-group/list/bottom/data");

            pageComponent1.setSaveAsUri("/admin/navbar/save-as");
            pageComponent2.setSaveAsUri("/admin/carousel/save-as");
            pageComponent3.setSaveAsUri("/admin/card-group/save-as");
            pageComponent4.setSaveAsUri("/admin/image-text-block-group/save-as");
            pageComponent5.setSaveAsUri("/admin/sort-link-group/save-as");
            pageComponent6.setSaveAsUri("/admin/full-width-image/save-as");
//            pageComponent7.setSaveAsUri("/admin/sort-link-group/bottom/save-as");
            pageComponent7.setSaveAsUri("/admin/sort-link-group/save-as");


            pageComponent1.setMaterialUploadUri("/admin/icon/upload-input");
            pageComponent2.setMaterialUploadUri("/admin/carousel/image/input");
            pageComponent3.setMaterialUploadUri("/admin/card-group/image/upload-input");
            pageComponent4.setMaterialUploadUri("/admin/image-text-block-group/image/upload-input");
            pageComponent5.setMaterialUploadUri("/admin/sort-link-group/image/input");
            pageComponent6.setMaterialUploadUri("/admin/full-width-image/image/upload-input");
//            pageComponent7.setMaterialUploadUri("/admin/sort-link-group/image/bottom/input");
            pageComponent7.setMaterialUploadUri("/admin/sort-link-group/image/input");


            List<PageComponent> pageComponentList=new ArrayList<PageComponent>();
            pageComponentList.add(pageComponent1);
            pageComponentList.add(pageComponent2);
            pageComponentList.add(pageComponent3);
            pageComponentList.add(pageComponent4);
            pageComponentList.add(pageComponent5);
            pageComponentList.add(pageComponent6);
            pageComponentList.add(pageComponent7);
            webPage.setPageComponents(pageComponentList);
            webPage.setUri("/home");
            webPage.setActive(true);
            pageComponentService.insertAll(pageComponentList);

            webPageService.insert(webPage);
        }

    }


}