package cn.iocoder.springboot.lab15.springdataelasticsearch.bo;

import java.util.List;

/**
 * 商品搜索条件返回 BO（业务对象层对象）
 */
public class ProductConditionBO {

    /**
     * 商品分类数组
     */
    private List<Category> categories;

    public static class Category {

        /**
         * 分类编号
         */
        private Integer id;
        /**
         * 分类名称
         */
        private String name;

        public Integer getId() {
            return id;
        }

        public Category setId(Integer id) {
            this.id = id;
            return this;
        }

        public String getName() {
            return name;
        }

        public Category setName(String name) {
            this.name = name;
            return this;
        }

        @Override
        public String toString() {
            return "Category{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public List<Category> getCategories() {
        return categories;
    }

    public ProductConditionBO setCategories(List<Category> categories) {
        this.categories = categories;
        return this;
    }

}
