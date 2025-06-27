interface userInfo {
    name: string;
    address: string;
    profile: string;
    mobile: string;
    id: string;
}
interface order {
    id: string;
    shopName: string;
    date: Date;
    weight: number;
    status: string;
}
interface payment{
    transactionID:string,
    date:Date,
    amount:number,
    paymentMethod:string,
    status:string,
    // action:
}
class SampleData {
    user: userInfo = {
        name: "Sourav",
        address: "Kaliswar",
        profile: "https://ik.imagekit.io/eur1zq65p/Contact/emoji9_cbqsta.png",
        id: "dfgdssdfg87dfgi87gfdhg",
        mobile: "7384510695",
    };

sampleOrder:order[] =[  {
    id: "order001",
    shopName: "Grocery Mart",
    date: new Date("2025-06-01T10:00:00Z"),
    weight: 20,
    status: "Pending",
},
{
    id: "order002",
    shopName: "Fresh Farm",
    date: new Date("2025-06-15T14:30:00Z"),
    weight: 35,
    status: "Delivered",
},
{
    id: "order003",
    shopName: "Dairy Hub",
    date: new Date("2025-06-20T09:00:00Z"),
    weight: 18,
    status: "In Progress",
},]

 samplePayments: payment[] = [
    {
      transactionID: "TXN001",
      date: new Date("2025-06-01T10:30:00Z"),
      amount: 1500,
      paymentMethod: "UPI",
      status: "Completed",
    },
    {
      transactionID: "TXN002",
      date: new Date("2025-06-03T14:15:00Z"),
      amount: 2499,
      paymentMethod: "Credit Card",
      status: "Pending",
    },
    {
      transactionID: "TXN003",
      date: new Date("2025-06-05T09:45:00Z"),
      amount: 999,
      paymentMethod: "Cash",
      status: "Failed",
    },
    {
      transactionID: "TXN004",
      date: new Date("2025-06-07T16:20:00Z"),
      amount: 2000,
      paymentMethod: "Net Banking",
      status: "Completed",
    },
    {
      transactionID: "TXN005",
      date: new Date("2025-06-09T11:00:00Z"),
      amount: 1250,
      paymentMethod: "Debit Card",
      status: "Refunded",
    },
  ];
  

;
}

export default new SampleData();
