import { BookmarksResponse } from "../services/models"
import React from "react"

import Pagination from "./Pagination"
import Bookmark from "./Bookmark"


interface BookmarksProps {
    bookmarks: BookmarksResponse
    query?: string
}

const Bookmarks: React.FC<BookmarksProps> = ({ bookmarks, query }) => (
    
    <div>
        <Pagination bookmarks={bookmarks} query={query}/>
        
        {
            bookmarks.data.map(
                (bookmark, i) => {
                    return <Bookmark bookmark={bookmark} key={i} />
                }
            )
        }

        {
            bookmarks.data.length === 0 &&
            <>
                <span className="alert alert-info">No results...</span>
            </>
        }

    </div>
)



export default Bookmarks
