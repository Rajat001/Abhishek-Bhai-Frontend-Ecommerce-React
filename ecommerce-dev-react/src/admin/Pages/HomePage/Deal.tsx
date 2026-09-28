import { Button } from "@mui/material";
import React, { useState } from "react";

import DealCategoryTable from "./DealCategoryTable";
import CreateDealFrom from "./CreateDealFrom";
import DealTable from "./DealTable";



const tabs=[
    "Deals",
    "Category",
    "Create Deal"
]


const Deal = () => {
    const [activeTab, setActiveTab] = useState("Deals");

    return (
        <div className="flex flex-col gap-4">

            {/* Tabs */}
            <div className="flex gap-4">
                {tabs.map((item) => (
                    <Button
                        key={item}
                        onClick={() => setActiveTab(item)}
                        variant={activeTab === item ? "contained" : "outlined"}
                    >
                        {item}
                    </Button>
                ))}
            </div>

            {/* Content */}
            <div className="mt-5">
                {activeTab === "Deals" ? (
                    <DealTable />
                ) : activeTab === "Category" ? (
                    <DealCategoryTable />
                ) : (
                    <div className="mt-5 flex flex-col justify-center items-center h-[70vh]">
                    <CreateDealFrom />
                    </div>
                )}
            </div>

        </div>
    );
};

export default Deal