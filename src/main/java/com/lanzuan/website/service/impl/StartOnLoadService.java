package com.lanzuan.website.service.impl;

import com.lanzuan.common.code.NavbarBrandTypeEnum;
import com.lanzuan.common.util.DateUtil;
import com.lanzuan.common.util.MD5;
import com.lanzuan.entity.*;
import com.lanzuan.entity.support.field.*;
import com.lanzuan.support.vo.Image;
import com.lanzuan.website.service.*;
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
    @Resource(name = "navbarService")
    private INavbarService navbarService;
    @Resource(name = "cardGroupService")
    private ICardGroupService cardGroupService;
    @Resource(name = "imageTextBlockGroupService")
    private IImageTextBlockGroupService imageTextBlockGroupService;
    @Resource(name = "fullWidthImageService")
    private IFullWidthImageService fullWidthImageService;
    @Resource(name = "userService")
    private IUserService userService;
    @Resource(name = "articlesAndImagesService")
    private IArticlesAndImagesService articlesAndImagesService;
//    private String[] componentVars={"navBar","carousel","cardGroup","imageTextBlockGroup","sortLinkGroup","fullWidthImage","bottomSortLinkGroup","services","articlesAndImages"};
    private String[] componentVars={"navBar","carousel","services","articlesAndImages","fullWidthImage","bottomSortLinkGroup"};
    /**
     * Spring 容器初始化时加载
     */
    public void loadData() {
        logger.info("容器初始化完成，载入初始化数据。。。。。。");
        initWebPages();
        initData();
    }

    private void initData() {

        initAdminData();
        initDirectory();
    }

    private void initDirectory() {

    }

    private void initAdminData() {
        long count=userService.count();
        if (count==0){
            User user=new User();
            user.setName("蓝钻科技");
            user.setLoginName("lzkj");
            user.setPassword(MD5.convert("lzkj"));
            userService.insert(user);
            logger.info("   没有管理员用户，添加默认管理员");
        }
    }



    private SortLinkGroup sortLinkGroup() {
        Date now=new Date();
        String _now=DateUtil.getCurrentYMD();
        SortLinkGroup sortLinkGroup=new SortLinkGroup();
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
        return sortLinkGroup;
    }
    private ImageTextBlockGroup services() {
        logger.info("初始化图文块。。。");
        ImageTextBlockGroup group=new ImageTextBlockGroup();
        group.setUri("/home");

        group.setName("ImageTextBlockGroup One");
        group.setText("典型应用");
        group.setEnabled(true);

        List<ImageTextBlock> imageTextBlocks=new ArrayList<ImageTextBlock>();
        ImageTextBlock block1=new ImageTextBlock();

        block1.setName("智慧城市");

        List<ImageTextItem> imageTextItems1=new ArrayList<ImageTextItem>();

        ImageTextItem imageTextItem1_1=new ImageTextItem();

        ImageTextItem imageTextItem1_2=new ImageTextItem();

        ImageTextItem imageTextItem1_3=new ImageTextItem();

        ImageTextItem imageTextItem1_4=new ImageTextItem();

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
        imageTextItems1.add(imageTextItem1_1);
        imageTextItems1.add(imageTextItem1_2);
        imageTextItems1.add(imageTextItem1_3);
        imageTextItems1.add(imageTextItem1_4);
        block1.setImageTextItems(imageTextItems1);
        imageTextBlocks.add(block1);
        group.setItems(imageTextBlocks);
        imageTextBlockGroupService.insert(group);
        return  group;
    }
    private ArticlesAndImages articlesAndImages() {
        Date now=new Date();
        ArticlesAndImages articlesAndImages=new ArticlesAndImages();
        articlesAndImages.setUri("/home");
        articlesAndImages.setEnabled(true);
        SortLink sortLink1=new SortLink();
        sortLink1.setSortName("新闻动态");
        List<Link> links1=new ArrayList<Link>();
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

        sortLink1.setLinks(links1);

        articlesAndImages.setArticles(sortLink1);
        ImageTextBlock images=new ImageTextBlock();
        images.setName("图片S");
        List<ImageTextItem> imageTextItems1=new ArrayList<ImageTextItem>();

        ImageTextItem imageTextItem1_1=new ImageTextItem();

        ImageTextItem imageTextItem1_2=new ImageTextItem();

        ImageTextItem imageTextItem1_3=new ImageTextItem();

        ImageTextItem imageTextItem1_4=new ImageTextItem();

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
        imageTextItems1.add(imageTextItem1_1);
        imageTextItems1.add(imageTextItem1_2);
        imageTextItems1.add(imageTextItem1_3);
        imageTextItems1.add(imageTextItem1_4);
        images.setImageTextItems(imageTextItems1);
        articlesAndImages.setImages(images);
        articlesAndImagesService.insert(articlesAndImages);
        return articlesAndImages;
    }
    private FullWidthImage fullWidthImage() {
        logger.info("初始化全屏宽度图片。。。");
        FullWidthImage fullWidthImage=new FullWidthImage();
        fullWidthImage.setName("政府大楼全屏宽度图");
        fullWidthImage.setEnabled(true);
        fullWidthImage.setUri("/home");
        Image image=new Image();
        image.setUri("/statics/image/lanzuan/full-width/zfdl.jpg");
        fullWidthImage.setImage(image);

        fullWidthImageService.insert(fullWidthImage);
        return fullWidthImage;
    }

    private ImageTextBlockGroup imageTextBlockGroup() {
        logger.info("初始化图文块组。。。");
        ImageTextBlockGroup group=new ImageTextBlockGroup();
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
        return  group;

    }

    private CardGroup cardGroup() {
        logger.info("初始化图文卡片组。。。");
        CardGroup cardGroup=new CardGroup();
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
        return cardGroup;

    }

    private Navbar navbar() {
        logger.info("初始化导航条。。。");
        Navbar navbar=new Navbar();
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
        return navbar;
    }

    private SortLinkGroup articleSectionData() {
        logger.info("初始化文章版块。。。");

        SortLinkGroup sortLinkGroup=new SortLinkGroup();
        sortLinkGroup.setEnabled(true);
        sortLinkGroup.setUri("/home");
        sortLinkGroup.setName("默认方案");
        sortLinkGroup.setIndexOfPage(0);
        Date now=new Date();
        String _now=DateUtil.getCurrentYMD();




        List<SortLink> sortLinks=new ArrayList<SortLink>();
        SortLink sortLink1 =new SortLink();
        sortLink1.setSortName("新闻动态");
        List<Link> links1=new ArrayList<Link>();
        Link link1=new Link();
        link1.setDate(now);
        link1.setHref("/article/"  );
        link1.setText("新闻1111111111111111111111111");
        links1.add(link1);
        sortLink1.setLinks(links1);
        sortLinks.add(sortLink1);


        SortLink sortLink2 =new SortLink();
        sortLink2.setSortName("企业文化");
        List<Link> links2=new ArrayList<Link>();
        Link link2=new Link();
        link2.setDate(now);
        link2.setHref("/article/" );
        link2.setText("新闻2222222222222222222222222222222222");
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
        return sortLinkGroup;
    }

    private Carousel carousel() {
        logger.info("初始化轮播图。。。");
        Carousel carousel=new Carousel();
        carousel.setName("首页轮播图");
        carousel.setDate(new Date());
        carousel.setUri("/home");
        carousel.setEnabled(true);
        List<CarouselItem> carouselItems=new ArrayList<CarouselItem>();
        CarouselCaption caption1=new CarouselCaption();
        caption1.setType("link");
        caption1.setText("了解更多");
        caption1.setHref("/statics/page/business/b1.html");
        CarouselItem carouselItem1=new CarouselItem();
        carouselItem1.setImageLink("/statics/image/lanzuan/home/carousel1.jpg");
        carouselItem1.setType("image");
        carouselItem1.setCarouselCaption(caption1);

        carouselItems.add(carouselItem1);


        CarouselCaption caption2=new CarouselCaption();
        caption2.setType("link");
        caption2.setText("了解更多");
        caption2.setHref("/statics/page/business/b2.html");
        CarouselItem carouselItem2=new CarouselItem();
        carouselItem2.setImageLink("/statics/image/lanzuan/home/carousel2.jpg");
        carouselItem2.setType("image");
        carouselItem2.setCarouselCaption(caption2);
        carouselItems.add(carouselItem2);

        CarouselCaption caption3=new CarouselCaption();
        caption3.setType("link");
        caption3.setText("了解更多");
        caption3.setHref("/statics/page/business/b2.html");
        CarouselItem carouselItem3=new CarouselItem();
        carouselItem3.setImageLink("/statics/image/lanzuan/home/carousel3.jpg");
        carouselItem3.setType("image");
        carouselItem3.setCarouselCaption(caption3);
        carouselItems.add(carouselItem3);

        CarouselCaption caption4=new CarouselCaption();
        caption4.setType("link");
        caption4.setText("了解更多");
        caption4.setHref("/statics/page/business/b2.html");
        CarouselItem carouselItem4=new CarouselItem();
        carouselItem4.setImageLink("/statics/image/lanzuan/home/carousel4.jpg");
        carouselItem4.setType("image");
        carouselItem4.setCarouselCaption(caption4);
        carouselItems.add(carouselItem4);
        carousel.setItems(carouselItems);
        carouselService.insert(carousel);
        return carousel;
    }

    private void initWebPages() {
        initHomePage();

    }



    private void initHomePage() {
        WebPage webPage = webPageService.findByUri("/home");
        if (webPage==null){
            logger.info("没有找到 /home 页面定义，应用默认定义......");
            webPage=new WebPage();

            PageComponent pageComponent1 = instanceByVar("navBar");
            PageComponent pageComponent2 = instanceByVar("carousel");
//            PageComponent pageComponent3 = instanceByVar("cardGroup");
//            PageComponent pageComponent4 = instanceByVar("imageTextBlockGroup");
//            PageComponent pageComponent5 = instanceByVar("sortLinkGroup");
            PageComponent pageComponent6 = instanceByVar("fullWidthImage");
            PageComponent pageComponent7 = instanceByVar("bottomSortLinkGroup");
            PageComponent pageComponent8 = instanceByVar("services");
            PageComponent pageComponent9 = instanceByVar("articlesAndImages");

            List<PageComponent> pageComponentList=new ArrayList<PageComponent>();
            pageComponentList.add(pageComponent1);
            pageComponentList.add(pageComponent2);
//            pageComponentList.add(pageComponent3);
//            pageComponentList.add(pageComponent4);
//            pageComponentList.add(pageComponent5);
            pageComponentList.add(pageComponent8);
            pageComponentList.add(pageComponent9);
            pageComponentList.add(pageComponent6);
            pageComponentList.add(pageComponent7);

            webPage.setPageComponents(pageComponentList);
            webPage.setUri("/home");
            webPage.setActive(true);

            pageComponent1.setData(navbar());
            pageComponent2.setData(carousel());
//            pageComponent3.setData(cardGroup());
//            pageComponent4.setData(imageTextBlockGroup());
//            pageComponent5.setData(articleSectionData());
            pageComponent6.setData(fullWidthImage());
            pageComponent7.setData(sortLinkGroup());
            pageComponent8.setData(services());
            pageComponent9.setData(articlesAndImages());

            pageComponentService.insertAll(pageComponentList);
            webPageService.insert(webPage);
        }else{
            rebuildComponents(webPage);
        }

    }

    private void rebuildComponents( WebPage webPage) {
        if (componentVars==null||componentVars.length==0) return;
        List<PageComponent> components=webPage.getPageComponents();
        List<PageComponent> rebuildComponents=new ArrayList<PageComponent>();
        int index=0;
        for (String var:componentVars){
            index++;
            PageComponent component=getByVar(var,components);
            if (component!=null){
                logger.info("现有组件 "+var+" 重新排序为"+index);

            }else{
                logger.info("新的组件 "+var+" 排序为"+index);
                component=instanceByVar(var);
                pageComponentService.insert(component);
            }
            rebuildComponents.add(component);
        }
        webPage.setPageComponents(rebuildComponents);
        webPageService.upsert(webPage);
    }

    private PageComponent instanceByVar(String var) {
        //"navBar","carousel","cardGroup","imageTextBlockGroup","sortLinkGroup","fullWidthImage","bottomSortLinkGroup","services","articlesAndImages"
        for (String currentVar:componentVars){
            if (var.equals(currentVar)){
                if (currentVar.equals("navBar")){
                    return instanceNavbarComponent();
                }else if(currentVar.equals("carousel")){
                    return instanceCarouselComponent();
                }else if(currentVar.equals("cardGroup")){
                    return instanceImageCardGroupComponent();
                }else if(currentVar.equals("imageTextBlockGroup")){
                    return instanceImageTextBlockGroupComponent();
                }else if(currentVar.equals("sortLinkGroup")){
                    return instanceSortLinkGroupComponent();
                }else if(currentVar.equals("fullWidthImage")){
                    return instanceFullWidthImageComponent();
                }else if(currentVar.equals("bottomSortLinkGroup")){
                    return instanceBottomSortLinkGroupComponent();
                }else if(currentVar.equals("services")){
                    return instanceServicesComponent();
                }else if(currentVar.equals("articlesAndImages")){
                    return instanceArticlesAndImagesComponent();
                }else {
                    return null;
                }
            }
        }
        return null;
    }

    private PageComponent instanceArticlesAndImagesComponent() {
        PageComponent pageComponent9=new PageComponent();
        pageComponent9.setWebsiteUri("/statics/page/included/lanzuan/articles-and-images-1.jsp");
        pageComponent9.setName("文章及图片");
        pageComponent9.setRemark("文章及图片");

        pageComponent9.setVar("articlesAndImages");
        pageComponent9.setDataUri("/articles-images/home/data");
        pageComponent9.setMaterialUploadUri("/admin/image-text-block-group/image/upload-input");
        pageComponent9.setMaterialUri("/admin/image-text-block-group/image/data");
        pageComponent9.setSaveAsUri("/admin/articles-images/save-as");
        pageComponent9.setSaveUri("/admin/articles-images/update");
        pageComponent9.setData(articlesAndImages());
        return pageComponent9;
    }

    private PageComponent instanceServicesComponent() {
        PageComponent pageComponent8=new PageComponent();
        pageComponent8.setName("图文卡片组模板2");
        pageComponent8.setRemark("一组新闻(第一条带图片)及一组图片，中等以上设备左右排列，中等以下设备上下排列。");
        pageComponent8.setDataUri("/image-text-block-group/home/data");

        pageComponent8.setToggleUri("/admin/image-text-block-group/status-change");
        pageComponent8.setDeleteUri("/admin/image-text-block-group/delete/");
        pageComponent8.setSaveUri("/admin/image-text-block-group/update");
        pageComponent8.setSaveAsUri("/admin/image-text-block-group/save-as");
        pageComponent8.setMaterialUploadUri("/admin/image-text-block-group/image/upload-input");
        pageComponent8.setMaterialUri("/admin/image-text-block-group/image/data");
        pageComponent8.setVar("services");
        pageComponent8.setWebsiteUri("/statics/page/included/lanzuan/image-title-text-block1.jsp");
        List<String> notes=new ArrayList<String>();
        notes.add("这个模块使用的是以前的多块图文项的第一块，这个可能会带来困惑但不会影响使用，所以编辑的时候请忽略后面的块。");
        pageComponent8.setEditNotes(notes);
        pageComponent8.setData(services());
        return pageComponent8;
    }

    private PageComponent instanceBottomSortLinkGroupComponent() {
        PageComponent pageComponent7=new PageComponent();
        pageComponent7.setName("分类链接模板1");
        pageComponent7.setRemark("分类链接模板，将许多链接分为多列排列，每列有个分类名称。在中等及以下屏幕每行显示2列；中等以上每行显示5列。");
        pageComponent7.setPreviewUri("/statics/page/included/lanzuan/sort-link-section-1.preview.jsp");
        pageComponent7.setWebsiteUri("/statics/page/included/lanzuan/sort-link-section-1.jsp");
        pageComponent7.setTemplateUri("/statics/page/included/component/template/sort-link-section-1.html");
        pageComponent7.setEditUri("/statics/page/included/lanzuan/article-section-1-edit.jsp");
        pageComponent7.setVar("bottomSortLinkGroup");
        pageComponent7.setDataUri("/sort-link-group/bottom/data");
        pageComponent7.setToggleUri("/admin/sort-link-group/status-change");
        pageComponent7.setDeleteUri("/admin/full-width-image/delete/");
        pageComponent7.setSaveUri("/admin/sort-link-group/update");
        pageComponent7.setListOperationUri("/admin/list-page/");
        pageComponent7.setListDataUri("/admin/sort-link-group/list/bottom/data");
        pageComponent7.setSaveAsUri("/admin/sort-link-group/save-as");
        pageComponent7.setMaterialUploadUri("/admin/sort-link-group/image/input");
        pageComponent7.setMaterialUri("/admin/sort-link-group/image/data");
        pageComponent7.setData(articleSectionData());
        return pageComponent7;
    }

    private PageComponent instanceFullWidthImageComponent() {
        PageComponent pageComponent6=new PageComponent();
        pageComponent6.setName("全屏宽度图片模板1");
        pageComponent6.setRemark("简单的全屏宽度图片。");
        pageComponent6.setPreviewUri("/statics/page/included/lanzuan/full-width-image-1.preview.jsp");
        pageComponent6.setWebsiteUri("/statics/page/included/lanzuan/full-width-image-1.jsp");
        pageComponent6.setTemplateUri("/statics/page/included/component/template/full-width-image-1.html");
        pageComponent6.setEditUri("/statics/page/included/lanzuan/full-width-image-1-edit.jsp");
        pageComponent6.setVar("fullWidthImage");
        pageComponent6.setDataUri("/full-width-image/home/data");
        pageComponent6.setToggleUri("/admin/full-width-image/status-change");
        pageComponent6.setDeleteUri("/admin/full-width-image/delete/");
        pageComponent6.setMaterialUri("/admin/full-width-image/image/data");
        pageComponent6.setListOperationUri("/admin/list-page/");
        pageComponent6.setListDataUri("/admin/full-width-image/list/data");
        pageComponent6.setSaveAsUri("/admin/full-width-image/save-as");
        pageComponent6.setMaterialUploadUri("/admin/full-width-image/image/upload-input");
        pageComponent6.setSaveUri("/admin/full-width-image/update");
        pageComponent6.setData(fullWidthImage());
        return pageComponent6;
    }

    private PageComponent instanceSortLinkGroupComponent() {
        PageComponent pageComponent5=new PageComponent();
        pageComponent5.setName("文章块组件1");
        pageComponent5.setRemark("文章块组件，中等及以下屏幕每行显示一列文字；中等以上每行显示3列新闻。");
        pageComponent5.setPreviewUri("/statics/page/included/lanzuan/article-section-1.preview.jsp");
        pageComponent5.setWebsiteUri("/statics/page/included/lanzuan/article-section-1.jsp");
        pageComponent5.setSaveAsUri("/admin/sort-link-group/save-as");
        pageComponent5.setMaterialUploadUri("/admin/sort-link-group/image/input");
        pageComponent5.setTemplateUri("/statics/page/included/component/template/article-section-1.html");
        pageComponent5.setEditUri("/statics/page/included/lanzuan/article-section-1-edit.jsp");
        pageComponent5.setVar("sortLinkGroup");
        pageComponent5.setDataUri("/sort-link-group/data");
        pageComponent5.setToggleUri("/admin/sort-link-group/status-change");
        pageComponent5.setDeleteUri("/admin/sort-link-group/delete/");
        pageComponent5.setSaveUri("/admin/sort-link-group/update");
        pageComponent5.setListOperationUri("/admin/list-page/");
        pageComponent5.setListDataUri("/admin/sort-link-group/list/data");
        pageComponent5.setMaterialUri("/admin/sort-link-group/image/data");
        pageComponent5.setData(sortLinkGroup());
        return pageComponent5;
    }

    private PageComponent instanceImageTextBlockGroupComponent() {
        PageComponent pageComponent4=new PageComponent();
        pageComponent4.setName("蓝钻鼠标掠过类似手风琴模板1");
        pageComponent4.setRemark("非标准bootstrap组件，需要依赖angularjs，效果为鼠标掠过按钮，在下方显示相应系列图片。在中等及以上屏幕每行显示4张图片，图片无边框效果；中等以下显示2张图片，图片带圆角相框效果。");
        pageComponent4.setListOperationUri("/admin/list-page/");
        pageComponent4.setListDataUri("/admin/image-text-block-group/list/data");
        pageComponent4.setPreviewUri("/statics/page/included/lanzuan/collapse-image-title-text-1.preview.jsp");
        pageComponent4.setWebsiteUri("/statics/page/included/lanzuan/collapse-image-title-text-1.jsp");
        pageComponent4.setTemplateUri("/statics/page/included/component/template/collapse-image-title-text-1.html");
        pageComponent4.setEditUri("/statics/page/included/lanzuan/collapse-image-title-text-1-edit.jsp");
        pageComponent4.setVar("imageTextBlockGroup");
        pageComponent4.setDataUri("/image-text-block-group/home/data");
        pageComponent4.setToggleUri("/admin/image-text-block-group/status-change");
        pageComponent4.setDeleteUri("/admin/image-text-block-group/delete/");
        pageComponent4.setSaveUri("/admin/image-text-block-group/update");
        pageComponent4.setSaveAsUri("/admin/image-text-block-group/save-as");
        pageComponent4.setMaterialUploadUri("/admin/image-text-block-group/image/upload-input");
        pageComponent4.setMaterialUri("/admin/image-text-block-group/image/data");
        pageComponent4.setData(imageTextBlockGroup());
        return pageComponent4;
    }

    private PageComponent instanceImageCardGroupComponent() {
        PageComponent pageComponent3=new PageComponent();
        pageComponent3.setName("图文卡片组模板1");
        pageComponent3.setRemark("一组带文字的图标组，在任何尺寸屏幕下皆保持一行。");
        pageComponent3.setPreviewUri("/statics/page/included/component/card-group/img-card-group-1.preview.jsp");
        pageComponent3.setWebsiteUri("/statics/page/included/component/card-group/img-card-group-1.jsp");
        pageComponent3.setTemplateUri("/statics/page/included/component/template/img-card-group-1.html");
        pageComponent3.setEditUri("/statics/page/included/component/card-group/img-card-group-1-edit.jsp");
        pageComponent3.setVar("cardGroup");
        pageComponent3.setSaveUri("/admin/card-group/update");
        pageComponent3.setDataUri("/card-group/home/data");
        pageComponent3.setToggleUri("/admin/card-group/status-change");
        pageComponent3.setDeleteUri("/admin/card-group/delete/");
        pageComponent3.setListOperationUri("/admin/list-page/");
        pageComponent3.setListDataUri("/admin/card-group/list/data");
        pageComponent3.setSaveAsUri("/admin/card-group/save-as");
        pageComponent3.setMaterialUploadUri("/admin/card-group/image/upload-input");
        pageComponent3.setMaterialUri("/admin/card-group/images/data");
        pageComponent3.setData(cardGroup());
        return pageComponent3;
    }

    private PageComponent instanceCarouselComponent() {
        PageComponent pageComponent2=new PageComponent();
        pageComponent2.setName("响应式轮播图");
        pageComponent2.setRemark("任何设备及屏幕都为全屏宽度。");
        pageComponent2.setPreviewUri("/statics/page/included/component/carousel/carousel-full-width-1.preview.jsp");
        pageComponent2.setWebsiteUri("/statics/page/included/component/carousel/carousel-full-width-1.jsp");
        pageComponent2.setTemplateUri("/statics/page/included/component/template/carousel-full-width-1.html");
        pageComponent2.setEditUri("/statics/page/included/component/carousel/carousel-full-width-1-edit.jsp");
        pageComponent2.setVar("carousel");
        pageComponent2.setDataUri("/carousel/home/data");
        pageComponent2.setToggleUri("/admin/carousel/update");
        pageComponent2.setDeleteUri("/admin/carousel/delete/");
        pageComponent2.setSaveUri("/admin/carousel/insert-all");
        pageComponent2.setListOperationUri("/admin/list-page/");
        pageComponent2.setListDataUri("/admin/carousel/list/data");
        pageComponent2.setSaveAsUri("/admin/carousel/save-as");
        pageComponent2.setMaterialUploadUri("/admin/carousel/image/input");
        pageComponent2.setMaterialUri("/admin/carousel-images/data");
        pageComponent2.setData(carousel());
        return pageComponent2;
    }

    private PageComponent instanceNavbarComponent() {
        PageComponent pageComponent1=new PageComponent();
        pageComponent1.setName("响应式导航条模板1");
        pageComponent1.setRemark("在中等及更小屏幕上会固定底部显示。");
        pageComponent1.setPreviewUri("/statics/page/included/component/navbar/navbar-md-down-fix-bottom.preview.jsp");
        pageComponent1.setWebsiteUri("/statics/page/included/component/navbar/navbar-md-down-fix-bottom.jsp");
        pageComponent1.setTemplateUri("/statics/page/included/component/template/navbar-md-down-fix-bottom.html");
        pageComponent1.setEditUri("/statics/page/included/component/navbar/navbar-md-down-fix-bottom-edit.jsp");
        pageComponent1.setDataUri("/navbar/home/data");
        pageComponent1.setVar("navBar");
        pageComponent1.setToggleUri("/admin/navbar/status-change");
        pageComponent1.setDeleteUri("/admin/navbar/delete/");
        pageComponent1.setSaveUri("/admin/navbar/update");
        pageComponent1.setListOperationUri("/admin/list-page/");
        pageComponent1.setListDataUri("/admin/navbar/list/data");
        pageComponent1.setSaveAsUri("/admin/navbar/save-as");
        pageComponent1.setMaterialUploadUri("/admin/icon/upload-input");
        pageComponent1.setMaterialUri("/admin/icons/data");
        pageComponent1.setData(navbar());
        return pageComponent1;
    }

    private PageComponent getByVar(String var, List<PageComponent> components) {
        for (PageComponent component:components){
            if (component.getVar()!=null&&component.getVar().equals(var)){
                return component;
            }
        }
        return null;
    }


}