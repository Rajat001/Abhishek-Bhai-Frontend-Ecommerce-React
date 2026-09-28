export const uploadToCloudinary=async(pics:any)=>{
    const cloud_name="nkauas01"
    const upload_preset="Sajeelirani_ecomm"

    if(pics){
        const data = new FormData();
        data.append("file",pics);
        data.append("upload_preset",upload_preset);
        data.append("cloud_name",cloud_name);
    
        const res = await fetch("http://api.cloudinary.com/v1_1/nkauas01/upload" , {
            method: "POST",
            body:data
        })

        const fileDate = await res.json();
        return fileDate.url;
    }
    else{
        console.log("error : pics not found");
    }
}