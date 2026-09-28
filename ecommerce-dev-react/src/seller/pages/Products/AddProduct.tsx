import React, { useState } from "react";
import {
    TextField,
    Button,
    MenuItem,
    Select,
    InputLabel,
    FormControl,
    FormHelperText,
    Grid,
    CircularProgress,
    IconButton,
    Snackbar,
    Alert
} from "@mui/material";

import {
    AddPhotoAlternate as AddPhotoAlternateIcon,
    Close as CloseIcon,
} from "@mui/icons-material";

import { menLevelTwo } from "../../../data/category/level two/menLevelTwo";
import { womenLevelTwo } from "../../../data/category/level two/womenLevelTwo";
import { furnitureLevelTwo } from "../../../data/category/level two/furnitureLevelTwo";
import { menLevelThree } from "../../../data/category/level three/menLevelThree";
import { womenLevelThree } from "../../../data/category/level three/womenLevelThree";
import { useFormik } from "formik";
import { title } from "process";
import { uploadToCloudinary } from "../../../util/uploadToCloudinary";
// import { colors } from "@mui/material";
import { colors } from "../../../data/Filter/color";
import { mainCategory } from "../../../data/category/mainCategory";


const categoryTwo : {[key:string] : any[]} = {
    men: menLevelTwo,
    women: womenLevelTwo,
    kids: [],
    home_furniture: furnitureLevelTwo,
    beauty: [],
    // electronics: electronicsLevelTwo,
};

const categoryThree: {[key: string] : any[] } = {
    men: menLevelThree,
    women: womenLevelThree,
    kids: [],
    home_furniture: furnitureLevelTwo,
    beauty: [],
}


const AddProductForm = () => {

    const [uploadImage, setUploadingImage] = useState(false);
    // const dispatch = useAppDispatch();
    // const {sellers, sellerProduct} = useAppSelectore(store => store);

    const [snackbarOpen, setOpenSnackbar] = useState(false);

    const formik = useFormik({
        initialValues: {
            title: "",
            description : "",
            mrpPrice: "",
            sellingPrice: "",
            quantity: "",
            color: "",
            images: [],
            category: "",
            category2:"",
            category3: "",
            sizes: "",
        },

        onSubmit: (values) => {
            console.log(values);
        },
    });

    const handleImageChange = async (event: any) => {
        const file = event.target.files[0];
        setUploadingImage(true);
        
        const image = await uploadToCloudinary(file);

        formik.setFieldValue("images", [...formik.values.images, image]);
        
        setUploadingImage(false);
    }

    const handleRemoveImage = (index: number) => {
        const updatedImages = [...formik.values.images];
        updatedImages.splice(index, 1);
        formik.setFieldValue("images", updatedImages);
    }

    const childCategory = (category: any, parentCategoryId: any) => {
        return category.filter((child: any) => {
            return child.parentCategoryId == parentCategoryId;
        });
    }

    return (
        <div>
            <form onSubmit={formik.handleSubmit} className="space-y-4 p-4">
                <Grid container spacing={2}>
                    <Grid className="flex flex-wrap gap-5"  size={{xs:12}}>
                        <input 
                            type="file" 
                            accept="image/*"
                            id="fileInput"
                            style={{display: "none"}}
                            onChange={handleImageChange} 
                        />

                        <label className="relative" htmlFor="fileInput">
                            <span className="w-24 h-24 cursor-pointer flex items-center 
                            justify-center p-3 border rounded-md border-gray-400">
                                <AddPhotoAlternateIcon className="text-gray-700"/>
                            </span>
                            {uploadImage && (
                                <div className="absolute left-0 right-0 top-0 bottom-0 w-24 h-24 flex 
                                justify-center items-center">
                                    <CircularProgress/>    
                                </div>
                            )}
                        </label>

                        <div className="flex flex-wrap gap-2">
                            {formik.values.images.map((image, index) => (
                                <div className="relative">
                                    <img className="w-24 h-24 object-cover" 
                                    key={index} 
                                    src={image} 
                                    alt={`ProductImage ${index + 1}`} />

                                    <IconButton
                                    onClick={() => handleRemoveImage(index)}

                                    className=""
                                    size="small"
                                    color="error"
                                    sx={{
                                        position: "absolute",
                                        top: 0,
                                        right: 0,
                                        outline: "none"
                                    }}
                                    >
                                        <CloseIcon sx={{ fontSize: "1rem"}}/>
                                    </IconButton>
                                </div>
                            ))}
                        </div>   

                        <Grid size={{xs:12}}>
                        <TextField
                            fullWidth
                            id="title"
                            name = "title"
                            label="Title"
                            value={formik.values.title}
                            onChange={formik.handleChange}
                            error={formik.touched.title && Boolean(formik.errors.title)}
                            helperText={formik.touched.title && formik.errors.title}
                            required
                        />
                        </Grid> 

                        <Grid size={{xs:12}}>
                        <TextField
                            multiline
                            rows={4}
                            fullWidth
                            id="description"
                            name = "description"
                            label="Description"
                            value={formik.values.description}
                            onChange={formik.handleChange}
                            error={
                                formik.touched.description && Boolean(formik.errors.description)
                                }
                            helperText={formik.touched.description && formik.errors.description}
                            required
                        />
                        </Grid> 

                                                <Grid size={{xs:12 , md:4, lg:3}}>
                        <TextField
                            fullWidth
                            id="mrp_price"
                            name = "mrpPrice"
                            label="MRP Price"
                            value={formik.values.mrpPrice}
                            onChange={formik.handleChange}
                            error={formik.touched.mrpPrice && Boolean(formik.errors.mrpPrice)}
                            helperText={formik.touched.mrpPrice && formik.errors.mrpPrice}
                            required
                        />
                        </Grid> 

                                                <Grid size={{xs:12 , md:4, lg:3}}>
                        <TextField
                            fullWidth
                            id="sellingPrice"
                            name = "sellingPrice"
                            label="Selling Price"
                            type="number"
                            value={formik.values.sellingPrice}
                            onChange={formik.handleChange}
                            error={
                                formik.touched.sellingPrice && 
                                Boolean(formik.errors.sellingPrice)
                                }
                            helperText={formik.touched.sellingPrice && formik.errors.sellingPrice}
                            required
                        />
                        </Grid> 

                         <Grid size={{xs:12 , md:4, lg:3}}>
                            <FormControl 
                            fullWidth
                            error={formik.touched.color && Boolean(formik.errors.color)} required>
                                <InputLabel id="color-label">Color</InputLabel>
                                <Select
                                    labelId="color-label"
                                    id="color"
                                    name="color"
                                    value={formik.values.color}
                                    onChange={formik.handleChange}
                                    label="Color"  
                                >
                                    <MenuItem value="">
                                        <em>None</em>
                                    </MenuItem>
{/* 10:06:11 */}
                                {colors.map((color, index) => (
                                    <MenuItem key={index} value={color.name}>
                                    <div className="flex gap-3">
                                    <span
                                    style={{ backgroundColor: color.hex }}
                                    className={`h-5 w-5 rounded-full ${
                                    color.name === "White" ? "border" : ""
                                    }`}
                                    ></span>

                                    <p>{color.name}</p>
                                    </div>
                                    </MenuItem>
                                ))}
                                </Select>
                                {formik.touched.color && formik.errors.color && (
                                    <FormHelperText>{formik.errors.color}</FormHelperText>
                                )}
                            </FormControl>        
                        </Grid> 

                                                <Grid size={{xs:12 , md:4, lg:3}}>
                        <FormControl
                        fullWidth
                        error={formik.touched.sizes && Boolean(formik.errors.sizes)} required>
                            <InputLabel id="sizes-label">Sizes</InputLabel>
                            <Select
                            labelId="sizes-label"
                            id="sizes"
                            >
                                    {/* In-Complete In-Complete In-Complete In-Complete  */}
                            </Select>
                        </FormControl>
                        </Grid> 

                                                <Grid size={{xs:12 , md:4, lg:4}}>
                        <FormControl
                            fullWidth
                            error={formik.touched.category && Boolean(formik.errors.category)} 
                            required
                        >
                            <InputLabel id="category-label">Category</InputLabel>
                            <Select
                            labelId="category-label"
                            id="category"
                            name="category"
                            value={formik.values.category}
                            onChange={formik.handleChange}
                            label="Category">

                                {mainCategory.map((item) => (
                                    <MenuItem value={item.categoryId}>{item.name}</MenuItem>
                                ))}

                            </Select>
                            </FormControl>                            
                        </Grid> 

                                                                        <Grid size={{xs:12 , md:4, lg:4}}>
                        <FormControl
                            fullWidth
                            error={formik.touched.category && Boolean(formik.errors.category)} 
                            required
                        >
                            <InputLabel id="category2-label">Second Category</InputLabel>
                            <Select
                            labelId="category2-label"
                            id="category"
                            name="category"
                            value={formik.values.category}
                            onChange={formik.handleChange}
                            label="Category">

                                {mainCategory.map((item) => (
                                    <MenuItem value={item.categoryId}>{item.name}</MenuItem>
                                ))}

                            </Select>
                            </FormControl>                            
                        </Grid> 

                                                                                                <Grid size={{xs:12 , md:4, lg:4}}>
                        <FormControl
                            fullWidth
                            error={formik.touched.category && Boolean(formik.errors.category)} 
                            required
                        >
                            <InputLabel id="category2-label">Third Category</InputLabel>
                            <Select
                            labelId="category-label"
                            id="category"
                            name="category3"
                            value={formik.values.category3}
                            onChange={formik.handleChange}
                            label="Category">

                                {mainCategory.map((item) => (
                                    <MenuItem value={item.categoryId}>{item.name}</MenuItem>
                                ))}

                            </Select>
                            </FormControl>                            
                        </Grid> 

                        <Grid size={{xs:12, md:4, lg:12}}>
                            <Button
                            sx={{p : "14px"}}
                            color="primary"
                            variant="contained"
                            fullWidth
                            type="submit"
                            >
                            {false? <CircularProgress size="small"
                            sx={{ width: "27px", height: "27px"}}/> : "Add Product"}        
                            </Button>
                        </Grid>


                    </Grid>
                </Grid>
            </form>
        </div>
    )
}


export default AddProductForm