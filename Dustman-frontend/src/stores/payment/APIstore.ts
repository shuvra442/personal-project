import axiosInstance from "../../config/axiosInstance";
interface Payment {
    amount: number;
    items: string;
}
class ApiStore {
    makePayment = async (paymentDetails: Payment) => {
        const form = {
            amount: paymentDetails.amount*100,
            items: paymentDetails.items,
        };
        console.log("form=>>",form);
        
        try {
          const response= await axiosInstance.post("/payment/make", form);
          console.log("response=>>",response);
          
          return  response.data;
        } catch (error: any) {
            console.log("Error=>  ",error);
            
        }
    };
    verifyPayment=async({payment_id,order_id,signature, }: {payment_id: string;order_id: string;signature: string;})=>{
        console.log("payment_id=>",payment_id," ",order_id," ",signature);
        const form = {
            orderId: order_id,
            paymentId: payment_id,
            razorpaySignature: signature,
        };
        try{

          const response= await axiosInstance.post("/payment/verify", form);
          console.log("response=>>",response);
        }catch(error:any){

        }

    }

    userDetails=async()=>{
        try {
          const response=  await axiosInstance.get("/user")
          console.log("response.data=>",response.data);
          
          return response.data;
        } catch (error:any) {
            
        }
    }
}
export default new ApiStore();
