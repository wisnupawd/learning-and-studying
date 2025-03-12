import NewsList from "@/components/news/news-list";
import {getAvailableNewsMonths, getAvailableNewsYears, getNewsForYear, getNewsForYearAndMonth} from "@/lib/news";
import Link from "next/link";
import {Suspense} from "react";

async function FilterHeader({year, month}) {
    const availableYears = await getAvailableNewsYears()
    let links = availableYears;
    if (year && !month) {
        links = getAvailableNewsMonths(year)
    } else if (year && month) {
        links = [];
    }

    if (year && !availableYears.includes(year) || month && !getAvailableNewsMonths(year).includes(month)) {
        throw new Error('Invalid filter.')
    }

    return <header id="archive-header">
        <nav>
            <ul>
                {links.map(link => {
                        const href = year ? `/archive/${year}/${link}` : `/archive/${link}`;
                        return <li key={link}>
                            <Link href={href}>{link}</Link>
                        </li>
                    }
                )}
            </ul>
        </nav>
    </header>
}

async function FilteredNews({year, month}) {
    let news;
    news = await getNewsForYear(year);
    if (year && !month) {
        news = await getNewsForYear(year)
    } else if (year && month) {
        news = await getNewsForYearAndMonth(year, month)
    }

    let newsContent = <p>No news found for the selected period.</p>

    if (news && news.length > 0) {
        newsContent = <NewsList news={news}/>
    }

    return newsContent;
}

export default async function FilteredNewsPage({params}) {
    let filter = params.filter;
    const selectedYear = filter?.[0];
    const selectedMonth = filter?.[1];



    return <>
        <Suspense fallback={<div>Loading filter...</div>}>
            <FilterHeader year={selectedYear} month={selectedMonth}/>
        </Suspense>

        <Suspense fallback={<div>Loading...</div>}>
            <FilteredNews month={selectedMonth} year={selectedYear} />
        </Suspense>
    </>
}