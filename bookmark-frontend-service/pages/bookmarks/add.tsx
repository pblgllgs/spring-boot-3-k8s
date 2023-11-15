import type { NextPage } from 'next'
import React, { useState } from 'react'
import { saveBookmark } from '../../services/api'
import { ToastContainer, toast } from 'react-toastify'
import 'react-toastify/dist/ReactToastify.css';


const AddBookmark: NextPage = () => {

  const [title, setTitle] = useState("")
  const [url, setUrl] = useState("")
  const [message, setMessage] = useState<string | null>(null)
  const handleSubmit = async (e: React.SyntheticEvent) => {
    e.preventDefault()
    if (!url) {
      alert("Please enter url!")
      return;
    }
    const payload = {
      title,
      url
    }
    try {
      await saveBookmark(payload)
      toast.success('Bookmark successfully saved!', {
        position: "top-right",
        autoClose: 2000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
        theme: "colored",
        });
      setTitle("")
      setUrl("")
    } catch (error) {
      toast.error('Error went try to save the bookmark', {
        position: "top-right",
        autoClose: 2000,
        hideProgressBar: false,
        closeOnClick: true,
        pauseOnHover: true,
        draggable: true,
        progress: undefined,
        theme: "colored",
        });
    }
    setMessage("Bookmark saved successfully!!")
  }
  return (
    <div>
      <ToastContainer
        position="top-right"
        autoClose={2000}
        hideProgressBar={false}
        newestOnTop={false}
        closeOnClick
        rtl={false}
        pauseOnFocusLoss
        draggable
        pauseOnHover
        theme="light"
      />
      <div className='card'>
        <div className='card-header text-center'>
          <h2>Create new bookmark</h2>
        </div>
        <div className='card-body'>
          <div className='card-text'>
            <form onSubmit={e => handleSubmit(e)}>
              <div className='mb-3'>
                <label htmlFor='url' className='form-label' id='url'>
                  URL
                </label>
                <input type="text" className='form-control' id="url" value={url} onChange={e => setUrl(e.target.value)} />
              </div>
              <div className='mb-3'>
                <label htmlFor='title' className='form-label' id='title'>
                  Title
                </label>
                <input type="text" className='form-control' id="title" value={title} onChange={e => setTitle(e.target.value)} />
              </div>
              <button type='submit' className='btn btn-primary' >Submit</button>
            </form>
          </div>
        </div>
      </div>
    </div>
  )
}

export default AddBookmark
