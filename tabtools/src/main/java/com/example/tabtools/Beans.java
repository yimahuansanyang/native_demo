package com.example.tabtools;

import java.util.List;

public class Beans {

    /**
     * date : 20181020
     * stories : [{"title":"每周一吸 · 狸花猫","ga_prefix":"102013","images":["https://pic4.zhimg.com/v2-9ef6021d279be280f7d2ba0788e392a7.jpg"],"multipic":true,"type":0,"id":9698943},{"images":["https://pic3.zhimg.com/v2-b752576f81b0a584bcf0e2ba96a07666.jpg"],"type":0,"id":9698988,"ga_prefix":"102012","title":"大误 · 救救马云"},{"images":["https://pic2.zhimg.com/v2-8502d8246509a273ba97133e0064176d.jpg"],"type":0,"id":9698610,"ga_prefix":"102010","title":"药瓶上的「肝肾功能不全者慎用」，到底想表达什么？"},{"images":["https://pic4.zhimg.com/v2-352a7a8539dfd84227b22c8574f327b7.jpg"],"type":0,"id":9698819,"ga_prefix":"102009","title":"孩子你终于长大了，2 岁正是学理财的好时候"},{"images":["https://pic1.zhimg.com/v2-bad6709dfc4abe08b9cacf5c75c03d00.jpg"],"type":0,"id":9698931,"ga_prefix":"102008","title":"「生命之桥」上，失落的人们选择在这里结束生命"},{"images":["https://pic4.zhimg.com/v2-1b3b1acab0c685beaa5428f33c7c6c03.jpg"],"type":0,"id":9698898,"ga_prefix":"102007","title":"作为电影导演 / 歌手，作品信息量这么大，我自己都不知道"},{"images":["https://pic3.zhimg.com/v2-d7a69c5474650820fce977064458d35e.jpg"],"type":0,"id":9699031,"ga_prefix":"102007","title":"13 岁少女肢解同窗好友，也许我们都小看她了"},{"images":["https://pic2.zhimg.com/v2-abe3c9ea2cdfe8051ea36154d30b4989.jpg"],"type":0,"id":9698984,"ga_prefix":"102006","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"https://pic4.zhimg.com/v2-9b704e39dc83ae7202fefc8d192e10f3.jpg","type":0,"id":9699031,"ga_prefix":"102007","title":"13 岁少女肢解同窗好友，也许我们都小看她了"},{"image":"https://pic2.zhimg.com/v2-66f79486c79deb9d779f9af99e719091.jpg","type":0,"id":9699060,"ga_prefix":"101921","title":"影视圈惊变 2018"},{"image":"https://pic4.zhimg.com/v2-e093102accf700073af6c3452293cd27.jpg","type":0,"id":9698862,"ga_prefix":"101719","title":"「56 个星座，56 支花」\u2026\u2026等等，不是 56 个民族吗？"},{"image":"https://pic4.zhimg.com/v2-76f03b3ab26c3d76be022a5281a13483.jpg","type":0,"id":9698744,"ga_prefix":"101810","title":"花海沦陷在抖音网红的臀下，想要提前避免，可能真没什么辙"},{"image":"https://pic3.zhimg.com/v2-5babbbaa37cccaf467b996e9bc1aadda.jpg","type":0,"id":9698809,"ga_prefix":"101807","title":"大学校园沦为艾滋病重灾区？一直如此，形势也越来越严峻"}]
     */

    private String date;
    private List<StoriesEntity> stories;
    private List<TopStoriesEntity> top_stories;

    public void setDate(String date) {
        this.date = date;
    }

    public void setStories(List<StoriesEntity> stories) {
        this.stories = stories;
    }

    public void setTop_stories(List<TopStoriesEntity> top_stories) {
        this.top_stories = top_stories;
    }

    public String getDate() {
        return date;
    }

    public List<StoriesEntity> getStories() {
        return stories;
    }

    public List<TopStoriesEntity> getTop_stories() {
        return top_stories;
    }

    public static class StoriesEntity {
        /**
         * title : 每周一吸 · 狸花猫
         * ga_prefix : 102013
         * images : ["https://pic4.zhimg.com/v2-9ef6021d279be280f7d2ba0788e392a7.jpg"]
         * multipic : true
         * type : 0
         * id : 9698943
         */

        private String title;
        private String ga_prefix;
        private boolean multipic;
        private int type;
        private int id;
        private List<String> images;

        public void setTitle(String title) {
            this.title = title;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public void setMultipic(boolean multipic) {
            this.multipic = multipic;
        }

        public void setType(int type) {
            this.type = type;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public String getTitle() {
            return title;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public boolean getMultipic() {
            return multipic;
        }

        public int getType() {
            return type;
        }

        public int getId() {
            return id;
        }

        public List<String> getImages() {
            return images;
        }
    }

    public static class TopStoriesEntity {
        /**
         * image : https://pic4.zhimg.com/v2-9b704e39dc83ae7202fefc8d192e10f3.jpg
         * type : 0
         * id : 9699031
         * ga_prefix : 102007
         * title : 13 岁少女肢解同窗好友，也许我们都小看她了
         */

        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public void setImage(String image) {
            this.image = image;
        }

        public void setType(int type) {
            this.type = type;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImage() {
            return image;
        }

        public int getType() {
            return type;
        }

        public int getId() {
            return id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public String getTitle() {
            return title;
        }
    }
}
