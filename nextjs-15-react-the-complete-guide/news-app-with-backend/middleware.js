import {NextResponse} from "next/server";

// reserve method name
export function middleware(request) {
  return NextResponse.next();
}

// reserve variable name
export const config = {
  matcher: "/news",
};
