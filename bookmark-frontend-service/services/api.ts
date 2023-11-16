import axios from "axios"
import { BookmarksResponse } from "./models"
import getConfig from "next/config"
const {serverRuntimeConfig,publicRuntimeConfig} = getConfig()

const getApiUrl = () => {
    return serverRuntimeConfig.API_BASE_URL || publicRuntimeConfig.API_BASE_URL;
}

export const fetchBookmarks = async (page:number, query: string): Promise<BookmarksResponse> => {
    let url = `${getApiUrl()}/api/v1/bookmarks?page=${page}`
    query ? url+=`&query=${query}` : ""
    const response = await axios.get<BookmarksResponse>(url)
    return response.data;
}

export const saveBookmark = async (bookmark: {title: string,url:string}) => {
    const response = await axios.post<BookmarksResponse>(`${getApiUrl()}/api/v1/bookmarks`,bookmark)
    return response.data;
}