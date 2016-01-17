package net.canwhn.common;

import java.util.List;

public class TulingJsonEntity {
	 /**
     * code : 308000
     * text : 亲，已帮您找到菜谱信息
     * list : [{"name":"鱼香肉丝","icon":"http://i4.xiachufang.com/image/280/cb1cb7c49ee011e38844b8ca3aeed2d7.jpg","info":"猪肉、鱼香肉丝调料 | 香菇、木耳、红萝卜、黄酒、玉米淀粉、盐","detailurl":"http://m.xiachufang.com/recipe/264781/"}]
     */

    private String code;
    private String text;
    /**
     * name : 鱼香肉丝
     * icon : http://i4.xiachufang.com/image/280/cb1cb7c49ee011e38844b8ca3aeed2d7.jpg
     * info : 猪肉、鱼香肉丝调料 | 香菇、木耳、红萝卜、黄酒、玉米淀粉、盐
     * detailurl : http://m.xiachufang.com/recipe/264781/
     */

    private List<ListEntity> list;

    public void setCode(String code) {
        this.code = code;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setList(List<ListEntity> list) {
        this.list = list;
    }

    public String getCode() {
        return code;
    }

    public String getText() {
        return text;
    }

    public List<ListEntity> getList() {
        return list;
    }

    public static class ListEntity {
        private String name;
        private String icon;
        private String info;
        private String detailurl;

        public void setName(String name) {
            this.name = name;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public void setDetailurl(String detailurl) {
            this.detailurl = detailurl;
        }

        public String getName() {
            return name;
        }

        public String getIcon() {
            return icon;
        }

        public String getInfo() {
            return info;
        }

        public String getDetailurl() {
            return detailurl;
        }

		@Override
		public String toString() {
			return "ListEntity [name=" + name + ", icon=" + icon + ", info=" + info + ", detailurl=" + detailurl + "]";
		}

		
        
    }

	@Override
	public String toString() {
		return "TulingJsonEntity [code=" + code + ", text=" + text + ", list=" + list + "]";
	}
    
    
}
