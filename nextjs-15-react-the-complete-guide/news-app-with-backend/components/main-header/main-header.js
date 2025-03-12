import Link from "next/link";
import MenuLink from "./menu-link";

export default function MainHeader() {
  return (
    <header id="main-header">
      <div id="logo">
        <Link href="/">NextNews</Link>
      </div>
      <nav>
        <ul>
          <li>
            <MenuLink href={"/news"}>News</MenuLink>
          </li>
          <li>
            <MenuLink href={"/archive"}>Archive</MenuLink>
          </li>
        </ul>
      </nav>
    </header>
  );
}
