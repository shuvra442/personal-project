export interface loginType {
    email: string
    password: string,
}

export interface regType {
  name: string;
  email: string;
  password: string;
  phoneNo?: string;       // optional
  address?: string;       // optional
  userImage?: File | null; // profile image
}


export interface User {
  userId: number;
  name: string;
  email: string;
  phoneNo?: string | null;
  address?: string | null;
  userImage?: string | null;
  role: "USER" | "OWNER" | "ADMIN";
  createdAt: string;
}