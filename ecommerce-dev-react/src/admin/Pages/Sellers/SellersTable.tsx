import { FormControl, InputLabel, MenuItem, Select } from "@mui/material";
import React from "react";

const SellersTable = () => {
    return (
        <div className="pb-5 w-60">
            <FormControl fullWidth>
            <InputLabel id="demo-simple-select-label">Age</InputLabel>
            <Select
            labelId="demo-simple-select-label"
            id="demo-simple-select"
            value={age}
            label="Age"
            onChange={handleChange}
            >
                <MenuItem value={10}>Ten</MenuItem>
            </Select>
            </FormControl>
        </div>
    )
}

export default SellersTable