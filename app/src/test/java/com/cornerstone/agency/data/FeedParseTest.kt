package com.cornerstone.agency.data

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class FeedParseTest {
    private val now = 1_700_000_000_000L

    @Test
    fun `parses a clean json array`() {
        val reply = """[{"topic":"AI models","title":"A","body":"b","link":"https://x.y/z"}]"""
        val posts = parseFeedPosts(reply, now)
        assertEquals(1, posts.size)
        assertEquals("A", posts[0].title)
        assertEquals("AI models", posts[0].topic)
        assertEquals("https://x.y/z", posts[0].link)
        assertEquals(now, posts[0].publishedAt)
    }

    @Test
    fun `strips code fences and surrounding prose`() {
        val reply = "Here you go:\n```json\n[{\"title\":\"One\"},{\"title\":\"Two\"}]\n```\nEnjoy!"
        val posts = parseFeedPosts(reply, now)
        assertEquals(listOf("One", "Two"), posts.map { it.title })
    }

    @Test
    fun `accepts alternate keys and drops bad links and untitled items`() {
        val reply = """[
            {"headline":"H","summary":"S","url":"ftp://nope","section":"Dev tools"},
            {"body":"no title here"},
            {"title":"OK","link":"http://ok"}
        ]"""
        val posts = parseFeedPosts(reply, now)
        assertEquals(2, posts.size)
        assertEquals("H", posts[0].title)
        assertEquals("S", posts[0].body)
        assertEquals("", posts[0].link)
        assertEquals("Dev tools", posts[0].topic)
        assertEquals("http://ok", posts[1].link)
    }

    @Test
    fun `order is preserved and timestamps descend so first stays newest`() {
        val posts = parseFeedPosts("""[{"title":"a"},{"title":"b"},{"title":"c"}]""", now)
        assertTrue(posts[0].publishedAt > posts[1].publishedAt)
        assertTrue(posts[1].publishedAt > posts[2].publishedAt)
    }

    @Test
    fun `non-json and non-array replies yield nothing`() {
        assertTrue(parseFeedPosts("I couldn't find anything today.", now).isEmpty())
        assertTrue(parseFeedPosts("""{"title":"not an array"}""", now).isEmpty())
        assertTrue(parseFeedPosts("[not json", now).isEmpty())
        assertTrue(parseFeedPosts("", now).isEmpty())
    }
}
