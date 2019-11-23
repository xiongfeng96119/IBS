package cn.itsource.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.List;
import java.util.UUID;

import cn.itsource.domain.Product;
import cn.itsource.query.ProductQuery;
import cn.itsource.service.IProductService;
import cn.itsource.utils.MyPage;
import cn.itsource.utils.ResultJson;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hpsf.Thumbnail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * (Product)Controller
 *
 * @author 吴昌勇
 * @since 2019-11-15 10:27:17
 */
@Controller
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService productService;
    
    @RequestMapping("/index")
    public String index(){
        return "product/product";
    }

    @ResponseBody
    @RequestMapping("/page")
    public MyPage<Product> page(ProductQuery productQuery){
        return productService.findByPage(productQuery);
    }

    @ResponseBody
    @RequestMapping("/save")
    public ResultJson save(Product product){
        try {
            if(product.getTypes().getId() == -1){
                throw new Exception("商品所属类型为必选！");
            }
            if(product.getUnit().getId() == -1){
                throw new Exception("商品的计量为必选！");
            }
            if(product.getBrand().getId() == -1){
                throw new Exception("商品的品牌为必选！");
            }
            if(StringUtils.isNotBlank(product.getPic())){
                product.setSmallpic(product.getPic() + "_small.png");
            }
            productService.save(product);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultJson(500,"新增失败：" + e.getMessage());
        }
        return new ResultJson(200,"新增成功！");
    }

    /**
     * @ModelAttribute注解表示当前类的其他所有方法执行之前先执行此方法
     * @param id
     * @return
     */
    @ModelAttribute("updateProduct")
    public Product findOneBeforeUpdate(Long id, String action){
        if(StringUtils.isNotBlank(action) && "update".equals(action) && id != null && id > 0){
            Product product = productService.findOne(id);
            //解决N to N错误
            product.setUnit(null);
            product.setBrand(null);
            product.setTypes(null);
            return product;
        }
        return null;
    }

    /**
     * 参数上加这个注解表示使用上面定义的@ModelAttribute注解的方法返回的对象作为基准，
     * 然后将表单提交过来的Employee对象身上所有非空属性值设置给上面方法返回的那个持久状态对象身上
     * if(StringUtils.isNotBlank(employee.getUsername())){
     *     employee1.setUsername(employee.getUsername());
     * }
     * @param product
     * @return
     */
    @ResponseBody
    @RequestMapping("/update")
    public ResultJson update(@ModelAttribute("updateProduct")Product product){
        try {
            if(product.getTypes().getId() == -1){
                throw new Exception("商品所属类型为必选！");
            }
            if(product.getUnit().getId() == -1){
                throw new Exception("商品的计量为必选！");
            }
            if(product.getBrand().getId() == -1){
                throw new Exception("商品的品牌为必选！");
            }
            if(StringUtils.isNotBlank(product.getPic())){
                product.setSmallpic(product.getPic() + "_small.png");
            }
            productService.update(product);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultJson(500,"修改失败：" + e.getMessage());
        }
        return new ResultJson(200,"修改成功！");
    }

    @ResponseBody
    @RequestMapping("/findOne")
    public Product findOne(Long id){
        return productService.findOne(id);
    }

    @ResponseBody
    @RequestMapping("/delete")
    public ResultJson delete(String ids){
        try {
            productService.delete(ids);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultJson(500,"删除失败：" + e.getMessage());
        }
        return new ResultJson(200,"删除成功！");
    }

    @ResponseBody
    @RequestMapping("/upload")
    public String upload(MultipartFile picFile, HttpServletRequest req){
        String original = picFile.getOriginalFilename();
        String ext = original.substring(original.lastIndexOf("."));	//获取后缀名
        String fileName = UUID.randomUUID().toString() + ext;		//生成一个新的文件名称
        String path1 = "G:/idea-workspace/IBS/src/main/webapp/pics";        //测试时用
        path1 += "/" + fileName;
        String path2 = req.getServletContext().getRealPath("pics");	    //获取真实物理路径 上线后用
        path2 += "/" + fileName;
        path2 = path2.replace("\\", "/");
        try {
            File file = new File(path2);
            File thumbnail = new File(file.getAbsolutePath() + "_small.png");
            //将文件数据写入文件中
            picFile.transferTo(file);
            //生成缩略图
            Thumbnails.of(file)     //原先的大图
                    .size(35, 35)   //缩略图尺寸
                    .toFile(thumbnail); //缩略图保存路径

            //将target下的pics中的图片copy到工作空间里面（上线之后这两句就直接去掉）
            Files.copy(file.toPath(), new FileOutputStream(path1));
            Files.copy(thumbnail.toPath(), new FileOutputStream(path1 + "_small.png"));
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "/pics/" + fileName;
    }

    /**
     * 查询所有商品，提供给采购单明细下拉框选择
     * @param productQuery
     * @return
     */
    @ResponseBody
    @RequestMapping("/findAll")
    public List<Product> findAll(ProductQuery productQuery){
        List<Product> list = productService.findAll(productQuery);
        list.add(0, new Product(-1L,"请选择商品"));
        return list;
    }


}