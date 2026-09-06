import { Button, Divider, FormControl, FormControlLabel, FormLabel, Radio, RadioGroup } from '@mui/material'
import { teal } from '@mui/material/colors'
import React, { useState } from 'react'
import { colors } from '../../../data/Filter/color'

const FilterSection = () => {

    const [expendColor, setExpendColor] = useState(false);
    const handleColorToggle = () => {
        setExpendColor(!expendColor);
    };

    return (
        <div className='-z-50 space-y-5 bg-white'>
            <div className='flex items-center justify-between h-[40px] px-9 lg:border-r'>
                <p className='text-lg font-semibold'> Filters </p>

                <Button size='small' className='text-teal-600 cursor-pointer font-semibold'>
                        Clear All
                </Button>
            </div>

            <Divider/>

         <div className='px-9 space-y-6'>
               <section>
<FormControl>
    <FormLabel
        sx={{
            fontSize: "16px",
            fontWeight: "bold",
            color: teal[500],
            pb: "14px"
        }}
        className="text-2xl font-semibold"
        id="color"
    >
        Color
    </FormLabel>

    <RadioGroup
        aria-labelledby="color"
        defaultValue=""
        name="color"
    >
        {colors.slice(0,expendColor?colors.length:5).map((item) => (
            <FormControlLabel
                key={item.name}
                value={item.name}
                control={<Radio />}
                label={
                    <div className="flex items-center gap-3">
                        <p>{item.name}</p>

                        <span
                            className={`h-5 w-5 rounded-full ${
                                item.name === "White" ? "border" : ""
                            }`}
                            style={{
                                backgroundColor: item.hex
                            }}
                        />
                    </div>
                }
            />
        ))}
    </RadioGroup>
</FormControl>         
        <div>
            <button 
            onClick={handleColorToggle}
            className='text-primary-color cursor-pointer hover:text-teal-900 flex items-center'>
                {expendColor?"hide": `+${colors.length-5} more`}
            </button>
        </div>
            </section>
         </div>

        </div>
    )
}

export default FilterSection
 