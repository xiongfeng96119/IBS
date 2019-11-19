package cn.itsource.ssj.controller;

import cn.itsource.ssj.domain.Product;
import cn.itsource.ssj.query.ProductQuery;
import cn.itsource.ssj.service.IProductService;
import cn.itsource.ssj.util.MyPage;
import cn.itsource.ssj.util.Result;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * (Product)controller
 *
 * @author 熊峰
 * @since 2019-11-15 11:12:27
 */
@Controller
@RequestMapping("/product")
public class ProductController{
    @Autowired
    private IProductService productService;
    @RequestMapping("/index")
    public String index(){
        return "product/product";
    }
    @RequestMapping("/page")
    @ResponseBody
    public MyPage<Product> page(ProductQuery productQuery){
        return productService.findPageByQuery(productQuery);
    }
    @ModelAttribute("findOneById")
    public Product findOneById(Long id,String action){
        if(StringUtils.isNotBlank(action)&& id != null && id > 0 && "update".equals(action)){
            Product product = productService.findOne(id);
            //让员工与关联的部门对象断开联系，解决N to N错误
            //employee.setDepartment(null);
            return product;
        }
        return null;
    }
    @RequestMapping("/findOne")
    @ResponseBody
    public Product findOne(Long id){
        return productService.findOne(id);
    }
    @RequestMapping("/save")
    @ResponseBody
    public Result save(Product product){
        try {
            productService.save(product);
        } catch (Exception e) {
            e.printStackTrace();
            Result result = new Result(500,"新增失败：" + e.getMessage());
        }
        return new Result(200, "新增成功！");
    }
    @RequestMapping("/update")
    @ResponseBody
    public Result update(@ModelAttribute("findOneById")Product product){
        try {
            productService.update(product);
        } catch (Exception e) {
            e.printStackTrace();
            Result result = new Result(500,"修改失败：" + e.getMessage());
        }
        return new Result(200, "修改成功！");
    }
    @RequestMapping("/delete")
    @ResponseBody
    public Result delete(String ids){
        try {
            productService.delete(ids);
            return new Result(200,"删除成功！");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(500,"删除失败：" + e.getMessage());
        }
    }

    /**
     * 文件上传
     * @param pic
     * @param request
     * @return
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping("/upload")
    public String upload(MultipartFile pic, HttpServletRequest request) throws IOException {
        String fileName = pic.getOriginalFilename();
        //获取文件名称后缀
        String ext = fileName.substring(fileName.lastIndexOf("."));
        //使用随机数+时间毫秒文件名称
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date today = new Date();
        String newName = sdf.format(today) + "-" + today.getTime();
        //上传文件路径在webapp下
        String realPath = request.getServletContext().getRealPath("/img/product/");
        File file = new File(realPath + newName + ext);
        pic.transferTo(file);
        //生成缩略图
        Thumbnails.of(file).size(38, 38).toFile(file.getAbsolutePath() + "_small.png");
        return "/img/product/" + newName + ext;
    }
    @RequestMapping("/findAll")
    @ResponseBody
    public List<Product> findAll(ProductQuery productQuery){
        List<Product> list = productService.findByQuery(productQuery);
        list.add(0,new Product(-1L,"请选择商品"));
        return list;
    }
}