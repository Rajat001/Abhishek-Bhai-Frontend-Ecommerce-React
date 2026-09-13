

import { Delete } from "@mui/icons-material";
import {
    Grid,
    Box,
    Avatar,
    Rating,
    IconButton
} from "@mui/material";
import { red } from "@mui/material/colors";
import React from "react";


const ReviewCard = () =>{

    return(
        <div className="flex justify-between ">
            <Grid container spacing={9}>
                <Grid size ={1}>
                    <Box>
                        <Avatar className='text-white' sx={{width:56, height:56, bgcolor:"#9155FD"}}>
                                Z
                        </Avatar>
                    </Box>
                </Grid>

                <Grid size ={{xs:9}}>
                    <div className="space-y-2">
                        <p className="font-semibold text-lg"> Zosh </p>
                        <p className="opacity-70"> 2024-09-27T23:16:07.478333</p>
                    </div>

                    <Rating 
                    readOnly
                    value={4}
                    precision={1}
                    />

                    <p>Value For Money Product, great Product</p>
                    <div>
                        <img className="w-24 h-24 object-cover" src="https://rukminim2.flixcart.com/image/1720/1720/xif0q/sari/q/t/m/free-red-bell-boughtnow-unstitched-original-imahdg2txmhsvdzb.jpeg?q=90" 
                        alt="" />
                    </div>
                </Grid>
            </Grid>

            <div>
                    <IconButton>
                            <Delete sx={{color:red[700]}}/>
                        </IconButton>
            </div>
    
        </div>
    )

}

export default ReviewCard